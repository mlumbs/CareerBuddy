package service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import data.Data;
import data.DatabaseCrud;
import data.JobContracts;


/**
 * Created by DevelopX on 2015-12-24.
 */
public class LoadService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p>
     * param name Used to name the worker thread, important only for debugging.
     */
    private static final String ACTION_RECOMMENT = "com.qghoha.developerx.careerbuddy.action.COMMENT";
    private static final String EXTRA_PARAM_STRING = "com.qghoha.developerx.careerbuddy.action.PARAM1";
    private static final String EXTRA_PARAM_CAT = "com.qghoha.developerx.careerbuddy.action.PARAM2";
    public static final String TAG = "Loading ";
    HashMap<String,Integer> bc;
    String report;
 

    public LoadService() {
        super("LoadService");
    }
    public static void startActionRECOMMENT(Context context, String param1,String param2) {
        Intent intent = new Intent(context, LoadService.class);
        intent.setAction(ACTION_RECOMMENT);
        intent.putExtra(EXTRA_PARAM_STRING, param1);
        intent.putExtra(EXTRA_PARAM_CAT, param2);
        context.startService(intent);
    }

    public static String[] Split(String sp){

        String split[] =sp.split(",");

        return  split;

    }
    public static int finds_method(String x){ //output the value of the method number given the rule
        return Integer.parseInt(String.valueOf(x.charAt(0)));
    }
    public static int extract_value(String s){ //Given s(subject) output value/level
        Log.v("core extractV==>",""+s);

        if(s.length()>2) {
            Log.v("core extractV==>",""+Integer.parseInt(String.valueOf(s.charAt(3))));
            return Integer.parseInt(String.valueOf(s.charAt(3)));

        }
        return 0;

    }
    public static String extract_subject(String s){//extract the subjects
       // Log.v("core extractS==>",""+s);
        if(s.length()>2){
            Log.v("core extractS==>",""+s.substring(0,3));
            return s.substring(0,3);
        }
        return " ";
    }
    public static String extract_subjecttwo(String s){
        if(s.length()>2){
            Log.v("core extractS==>",""+s.substring(0,3));
            return s.substring(0,3);
        }
        return " ";
    }
    public static String compress(String ab[]){
        String ig="";

        for(int i=0;i<ab.length;i++){
            ig+=toSubjectString(ab[i]);
        }
        return ig;
    }
    @Override
    protected void onHandleIntent(Intent intent) {


        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_RECOMMENT.equals(action)) {
                bc=new HashMap<String,Integer>();

                final String param1 = intent.getStringExtra(EXTRA_PARAM_STRING);
                final String param2 = intent.getStringExtra(EXTRA_PARAM_CAT);
                report="";
                InitiateMap();
                handleActionRECOMMEND(param1,param2);
            }
        }


    }


    private  void InitiateMap(){
        Cursor c = getContentResolver().query(JobContracts.ReportEntry.CONTENT_URI,null,null,null,null);

        while(c.moveToNext()&&!c.isAfterLast()){
            String sub = c.getString(c.getColumnIndex(JobContracts.ReportEntry.COLUMN_EA));//short code
            String mark = c.getString(c.getColumnIndex(JobContracts.ReportEntry.COLUMN_EB));//Long name
            String mark2 = c.getString(c.getColumnIndex(JobContracts.ReportEntry.COLUMN_EC));//number

             bc.put(sub,Integer.valueOf(mark2));
            Log.v("Map",mark2+" "+sub+" "+mark) ;
           //               2       ACC    Accounting
        }

    }

    private void handleActionRECOMMEND(String param1,String param2) {

        try {

            JSONObject Json = new JSONObject(param1);
            Log.v("Core",""+Json) ;
            if (!Json.isNull("entries"))
            {
                boolean re=false;
                Log.v("Core", "This is not null");
                JSONArray JArray = Json.getJSONArray("entries");//This return null if network is unavailable
                for (int z = 0; z < JArray.length(); z++) {
                    Log.v("Core z values", ""+z);
                    String id = JArray.getJSONObject(z).getString("id");
                    Log.v("Core id values", ""+id);
                    String a = JArray.getJSONObject(z).getString("care_val");
                    Log.v("Core a values", ""+a);
                    String b = JArray.getJSONObject(z).getString("Degree_name");
                    Log.v("Core b values", ""+b);
                    String c = JArray.getJSONObject(z).getString("point");
                    Log.v("Core c values", ""+c);
                    String d = JArray.getJSONObject(z).getString("inst");
                    Log.v("Core d values", ""+d);
                    String e = JArray.getJSONObject(z).getString("MAT");
                    Log.v("Core e values", ""+e);
                    String f = JArray.getJSONObject(z).getString("MAL");
                    Log.v("Core f values", ""+f);
                    String g = JArray.getJSONObject(z).getString("ENG");
                    Log.v("Core g values", ""+g);
                    String h = JArray.getJSONObject(z).getString("LFO");
                    Log.v("Core h values", ""+h);
                    String i = JArray.getJSONObject(z).getString("r1");
                    Log.v("Core i values", " "+i);
                    String j = JArray.getJSONObject(z).getString("r2");
                    Log.v("Core j values", " "+j);
                    String k = JArray.getJSONObject(z).getString("r3");
                    Log.v("Core k values", " "+k);
                    String l = JArray.getJSONObject(z).getString("r4");
                    Log.v("Core l values", " "+l);
//                    String m = JArray.getJSONObject(z).getString("r5");
//                    Log.v("Core m values", " "+m);
                   // if(JArray.getJSONObject(z).getString("r1")!=""){
                       // i=JArray.getJSONObject(z).getString("r1");

                    re=r1(i)&&r2(j)&&r3(k)&&r4(l)&&Gen(extract_value(g),extract_value(e),extract_value(h));
                    //re=Gen(extract_value(g),extract_value(e),extract_value(h));
                    //Log.v("core ==>"," r1"+r1(i)+"r2"+r2(j)+"r3"+r3(k)+"r4"+r4(l)+"gen"+Gen(extract_value(g),extract_value(e),extract_value(h)));
                        Log.v("core ==>"," "+extract_subject(i));
                    Log.v("core ==>","  "+extract_value(i));

                  //DatabaseCrud.ToAddRecommendationsEntry(this,id,param2,equate(re)+"",d,b,c,f,g,h);
                    DatabaseCrud.ToAddRecommendationsEntry(this,id,param2,report,equate(re)+"",d,b,c,"","");

                    //Log.v("core",""+b+"  "+c+" "+d+" "+e+" "+f+" "+g+" "+h);
                    report="";
                }

            }else{
                Log.v("core","entries is empty");
            }
        }catch (Exception e){
            Log.v("Core","Error,exception"+e.getMessage());
        }



    }

    public String equate(boolean t){
        return t? "successful":"unsuccessful";
    }

    private String loadJsonFromNetworkSecond(String urlString) throws IOException {

        //Log.v(TAG,urlString);
        URL url = new URL(urlString);
        String JsonString = null;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();


            if (inputStream == null) {
                // Nothing to do.
                JsonString = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                JsonString = null;
            }
            JsonString = buffer.toString();


        } catch (IOException e) {
            e.getMessage();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return JsonString;//returns JSON
    }


    public void getDataFromJson(String JsonStr) throws JSONException {
        JSONObject Json = new JSONObject(JsonStr);
        if (!Json.isNull("entries")) {
            Log.v("Z3", "This is not indeed really null");
            JSONArray JArray = Json.getJSONArray("entries");


        }


    }

    public boolean r1(String b){//accept rule

        if(b.length()<3)
            return true;

        int trace=0;
        String g[] =Split(b);
        for(int i=0;i<g.length;i++){
           Log.v("R1",extract_subject(g[i]));
            if(bc.containsKey(extract_subject(g[i]))) {//find the subject
                Log.v("R1",extract_subject(g[i]));
                if(extract_value(g[i]) <= bc.get(extract_subject(g[i]) )  ){
                    trace++;
                   // report+="  You dont meet minimum level for "+compress(g);
                }

            }else{
               //report+="  You dont have "+compress(g);
            }
        }

        if (trace>0) {
            return true	;
        }else {
            report+="R1 You don't meet minimum requirements for "+compress(g);
            return false;
        }


    }


    public boolean r2(String b){//accept rule

        if(b.length()<3)
            return true;

        int trace=0;
        String g[] =Split(b);
        for(int i=0;i<g.length;i++){
            System.out.println("E1 "+extract_subject(g[i]));
            if(bc.containsKey(extract_subject(g[i]))) {//find the subject
                //System.out.println("meet subject");

                //System.out.println(extract_value(g[i])+"<Report" + bc.get(extract_subject(g[i])));

                if(extract_value(g[i]) <= bc.get(extract_subject(g[i]) )  ){
                    trace++;
                    System.out.println("meet level"+trace);
                }

            }
        }
       // System.out.println("t"+trace+" "+g.length);
        if(g.length==trace)
            return true;
        else return false;

    }


    /*R3 ,this method is not specific to an subject but only checks for levels required
     * excludes Gen subject for checking
     * thus only receive and utilizes only the size of the array
     * Dont count LO and ENG
     * Receives RRR
     */
    public  boolean r3(String b){ //any subject meeting specific level,the number of subject must be equal to the size of the array
        if(b.length()<3)
            return true;
        int trace=0;
        String g[] =Split(b);
        for(Map.Entry<String, Integer> entry : bc.entrySet()){
            String key = entry.getKey();
            if(key=="ENG" ||key=="LFO") {
                System.out.println("Enlish detetcted");
            }
            else {
                if(entry.getValue()>=extract_value(g[0]))
                    trace++;

                System.out.println(extract_value(g[0])+" value");
            }

        }
        System.out.println(extract_value(g[0])+" value");
        System.out.println(trace+" length");
        if(trace>=g.length) {
            System.out.println("ok");
            return true;
        } else {
            System.out.println("not ok");
            return false;
        }

    }

    public boolean Gen(int eng,int math,int Lo) {
        report+="general";
        if((bc.get("ENG")!=null&&bc.get("MAT")!=null&&bc.get("LFO")!=null)||(bc.get("ENG")!=null&&bc.get("MAL")!=null&&bc.get("LFO")!=null) ) {
            if (eng > bc.get("ENG") && eng != 0) {
                report += "English level is not met";
                return false;
            }

            if(bc.get("MAT")!=null)
            if (math > bc.get("MAT") && math != 0) {
                report += "Math level is not met";
                return false;
            }

            if(bc.get("MAL")!=null)
            if (math > bc.get("MAL") && math != 0) {
                report += "MATH level is not met";
                return false;
            }

            if (Lo > bc.get("LFO") && Lo != 0) {
                report += "LIFE ORIENTATION level is not met";
                return false;
            }
        }
        else{
            report+="You need to enter English or LO or Maths in your report";
            return  false;
        }
        return true;

    }



    /*Each subject specified in g will be looked and once found we exit once
    * One of Subjects
    */
    public  boolean r4(String b){
        if(b.length()<3)
            return true;

        int trace=0;
        String g[] =Split(b);
        for(int i=0;i<g.length;i++){
            if(bc.containsKey(extract_subject(g[i]))) {//find the subject
                System.out.println("meet subject");
                if(extract_value(g[i]) <= bc.get(extract_subject(g[i]) )  ){
                    trace++;
                    System.out.println("meet level");
                    return true	;
                }

            }else {
            }

        }
        return false;
    }




    public static String toSubjectString(String c){
       c=extract_subjecttwo(c);
        //Log.v("sub",c+"  "+extract_subjecttwo(c));
        switch (c){
            case "DRA" :
                return "Dramatic Arts";
            case "ECO" :
                return "Economics";
            case "ETE" :
                return "Electrical Technology";
            case "EGD" :
                return "Engineering Graphics Design";
            case "GEO" :
                return "Geography";
            case "HIS" :
                return "History";
            case "HST" :
                return "Hospitality Studies";
            case "ITE" :
                return "Information Technology";
            case "LFS" :
                return "Life Sciences";
            case "MET" :
                return "Mechanical Technology";
            case "MSC" :
                return "Music";
            case "PHY" :
                return "Physical Science";
            case "RST" :
                return "Religion Studies";
            case "VSA" :
                return "Visual Arts";
            case "TRM" :
                return "Tourism";
            case "MAT" :
                return "Mathematics";
            case "MAL" :
                return "Mathematical Literacy";
            case "LFO" :
                return "Life Orientation";
            case "ACC" :
                return "Accounting";
            case "AMP" :
                return "Agricultural Management Practices";
            case "AGR" :
                return "Agricultural Sciences";
            case "AGT" :
                return "Agricultural Technology";
            case "BSS" :
                return "Business Studies";
            case "CIT" :
                return "Civil Technology";
            case "CAT" :
                return "Computer Applications Technology";
            case "CST" :
                return "Consumer Studies";
            case "DST" :
                return "Dance Studies";
            case "DES" :
                return "Design";
            case "HIN" :
                return "Hindi";
            case "GUJ" :
                return "Gujarati";
            case "TAM" :
                return "Tamil";
            case "TEL" :
                return "Telegu";
            case "ARA" :
                return "Arabic";
            case "FRE" :
                return "French";
            case "ITA" :
                return "Italian";
            case "SPA" :
                return "Spanish";
            case "MDG" :
                return "Modern Greek";
            case "LAT" :
                return "Latin";
            case "POR" :
                return "Portuguese";
            case "GER" :
                return "German";
            case "HEB" :
                return "Hebrew";
            case "URB" :
                return "Urdu";
            case "SER" :
                return "Serbian";
            case "ENG" :
                return "English";
            case "ISZ" :
                return "isiZulu";
            case "ISX" :
                return "isiXhosa";
            case "SIW" :
                return "Siswati";
            case "SNB" :
                return "isiNdebele";
            case "AFR" :
                return "Afrikaans";
            case "SEP" :
                return "Sepedi";
            case "SET" :
                return "Sesotho";
            case "SEW" :
                return "Setswana";
            case "XIT" :
                return "Xitsonga";
            case "TSH":
                return "Tshivenda";
            case "NAS":
                return "Nautical Science";
            case "EQS":
                return "Equine Studies";
            case "SES":
                return "Sport and Exercise Science";
            case "MAE":
                return "Maritime Economics";
            default:
                return "Not known Sub";
        }

    }




}
