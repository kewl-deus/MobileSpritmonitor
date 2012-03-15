package de.dengot.spritmonitor.io;


import android.util.Log;
import de.dengot.spritmonitor.model.Fueling;
import de.dengot.spritmonitor.model.FuelingBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <ul>
 * <li><strong>Datum</strong>: TT.MM.JJJJ (z.B. 23.02.2010)</li>
 * <li><strong>Tachostand, Distanz, Spritmenge, Gesamtkosten</strong>: Numerisch ohne Tausendertrennzeichen</li>
 * <li><strong>Währung</strong>: Internationales Standard-Kürzel (z.B. EUR, CHF, USD) </li>
 * <li><strong>Tankart</strong>: 0=ungültig, 1=Vollbetankung, 2=Teilbetankung, 3=Erstbetankung</li>
 * <li><strong>Reifen</strong>: 1=Winterreifen, 2=Sommerreifen, 3=Ganzjahresreifen</li>
 * <li><strong>Strecke</strong>: Summe aus 2=Autobahn, 4=Stadt, 8=Landstraße (z.B. Autobahn und Landstraße: 10)</li>
 * <li><strong>Fahrweise</strong>: 1=bewusst sparsam, 2=normal, 3=flott</li>
 * <li><strong>Kraftstoff</strong>: 1=Diesel, 2=Biodiesel, 3=Pflanzenöl, 4=Premium Diesel, 6=Normalbenzin, 7=Super,
 * 8=SuperPlus, 9=Premium Benzin 100, 12=LPG, 13=CNG H, 14=CNG L, 15=Bioethanol, 16=Zweitakt-Gemisch, 18=Premium
 * Benzin 95, 19=Elektrizität, 20=E10
 * </li>
 * <li><strong>Bemerkung</strong>: Text</li>
 * </ul>
 */
public class FuelingCsvParser extends FuelingParser {

    //Datum;Km-Stand;Teil-Km;Spritmenge;Kosten;Währung;Tankart;Reifen;Strecken;Fahrweise;Kraftstoff;Bemerkung;Verbrauch
    //11.11.2009;71060,00;388,00;52,19;72,49;"EUR";1;1;14;2;8;"";13,45

    private static final int FILLDATE = 0; //Datum
    private static final int ODOMETER = 1; //Km-Stand
    private static final int DISTANCE = 2; //Teil-Km
    private static final int QUANTITY = 3; //Spritmenge
    private static final int COST = 4; //Kosten
    private static final int FILLUP = 6; //Tankart

    private SimpleDateFormat dateFormat;
    private NumberFormat floatFormat;

    public FuelingCsvParser(FuelingWriter writer) {
        super(writer);
        dateFormat = new SimpleDateFormat("dd.MM.yy");
        floatFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
    }

    @Override
    public void parseFuelings(Reader reader) throws IOException {
        LineNumberReader lineReader = new LineNumberReader(reader);
        String headline = lineReader.readLine();
        while (lineReader.ready()) {
            String csv = lineReader.readLine();
            String[] tokens = readTokens(csv);
            try {
                Fueling fueling = map(tokens);
                writeFueling(fueling);
            } catch (ParseException e) {
                Log.e("FuelingCsvParser",
                        MessageFormat.format("Parsing of csv-line #{0} failed, skipping it", lineReader.getLineNumber()), e);
            }
        }
    }

    private String[] readTokens(String csv) {
        List<String> tokenList = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(csv, ";", false);
        while (tokenizer.hasMoreTokens()) {
            tokenList.add(tokenizer.nextToken());
        }
        return tokenList.toArray(new String[tokenList.size()]);
    }


    private Fueling map(String[] tokens) throws ParseException {
        FuelingBean f = new FuelingBean();
        f.setCost(parseFloat(tokens[COST]));
        f.setDistance(parseInt(tokens[DISTANCE]));
        f.setFilldate(parseDate(tokens[FILLDATE]));
        f.setOdometer(parseInt(tokens[ODOMETER]));
        f.setQuantity(parseFloat(tokens[QUANTITY]));
        f.setFillup(parseInt(tokens[FILLUP]) == 1);
        return f;
    }

    private Date parseDate(String token) throws ParseException {
        return dateFormat.parse(token);
    }

    private float parseFloat(String token) throws ParseException {
        return floatFormat.parse(token).floatValue();
    }

    private int parseInt(String token) throws ParseException {
        return floatFormat.parse(token).intValue();
    }

    private long parseLong(String token) throws ParseException {
        return floatFormat.parse(token).longValue();
    }
}
