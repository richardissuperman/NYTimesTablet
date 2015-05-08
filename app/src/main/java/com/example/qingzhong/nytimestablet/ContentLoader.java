package com.example.qingzhong.nytimestablet;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by qingzhong on 9/4/15.
 */
public class ContentLoader extends AsyncTask<String,Integer,String>{

    public Fragment fragmentContext;

    public ContentLoader(Fragment context){
        this.fragmentContext=context;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        //super.onProgressUpdate(values);
       // values[0];
        Log.e("progress",values[0]+"%");
        //((ContentFragment)this.fragmentContext).testText.setText(values[0]+"%");
        if(values[0]==99){


            ((ContentFragment)this.fragmentContext).loadingImg.setImageResource(0);


            return;

        }

        //int img=R.drawable.abc;
        ((ContentFragment)this.fragmentContext).loadingImg.setImageResource(R.drawable.ajaxloader);


    }



    @Override
    protected String doInBackground(String... params) {



       // URL url;
        HttpURLConnection urlConnection;
        //StringBuffer buffer=new StringBuffer()

        BufferedReader reader;

        String pagePararmeter=params[1];


        Log.e("Parameters",params[1]);


        //String line=null;



                try {
                    // Construct the URL for the OpenWeatherMap query
                    // Possible parameters are avaiable at OWM's forecast API page, at
                    // http://openweathermap.org/API#forecast
                    publishProgress(10);
                    Log.e("inputstream", "1");
                    URL url = new URL(ContentContract.basePath+ContentContract.articleParamter+ContentContract.articleKeyParameter+"="+ContentContract.articleKey+"&page="+pagePararmeter);
                    Log.e("URL is",ContentContract.basePath+ContentContract.articleParamter+ContentContract.articleKeyParameter+"="+ContentContract.articleKey+"&page="+pagePararmeter);
                    Log.e("inputstream", "1.1");
                    // Create the request to OpenWeatherMap, and open the connection
                    urlConnection = (HttpURLConnection) url.openConnection();
                    Log.e("inputstream", "1.2");
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    Log.e("inputstream", "1.3");

                    Log.e("inputstream", "2");

                    publishProgress(40);

                    // Read the input stream into a String
                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();
                    if (inputStream == null) {
                        // Nothing to do.
                        Log.e("inputstream", "3");

                        return null;
                    }

                    Log.e("inputstream", "4");

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                        // But it does make debugging a *lot* easier if you print out the completed
                        // buffer for debugging.
                        buffer.append(line + "\n");
                        //Log.e("content",line);
                    }

                    publishProgress(99);



                    return buffer.toString();


                } catch (Exception e) {
                    Log.e("internet Error",Log.getStackTraceString(e));
                 //   Log.getStackTraceString(e);

                }


                return null;



        }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

     //   Log.d("richard's result!",s.toString());



        if(s==null){
            Toast.makeText(this.fragmentContext.getActivity(),"no result return!",Toast.LENGTH_SHORT).show();
            //s="{\"response\":{\"meta\":{\"hits\":3707394,\"time\":748,\"offset\":0},\"docs\":[{\"web_url\":\"http:\\/\\/www.nytimes.com\\/2015\\/04\\/19\\/magazine\\/the-cost-of-sally-manns-exposure.html\",\"snippet\":\"What an artist captures, what a mother knows and what the public sees can be dangerously different things.\",\"lead_paragraph\":\"What an artist captures, what a mother knows and what the public sees can be dangerously different things.\",\"abstract\":\"Sally Mann article reflects on controversy surrounding her 1992 book Immediate Family, which featured photos of her children; highlights loaded issues photographs seemed to have raised, like nature of desire and appropriateness of publishing photos of children when naked.\",\"print_page\":\"49\",\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-articleLarge.jpg\",\"height\":338,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-articleLarge.jpg\",\"xlargeheight\":\"338\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/16\\/magazine\\/19mag-mann-cover-portrait\\/19mag-mann-cover-portrait-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"Sally Mann\\u2019s Exposure\",\"print_headline\":\"Exposure\"},\"keywords\":[{\"rank\":\"1\",\"is_major\":\"Y\",\"name\":\"persons\",\"value\":\"Mann, Sally\"},{\"rank\":\"2\",\"is_major\":\"Y\",\"name\":\"subject\",\"value\":\"Photography\"},{\"rank\":\"3\",\"is_major\":\"Y\",\"name\":\"subject\",\"value\":\"Books and Literature\"},{\"rank\":\"4\",\"is_major\":\"N\",\"name\":\"subject\",\"value\":\"Children and Childhood\"},{\"rank\":\"5\",\"is_major\":\"N\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"6\",\"is_major\":\"N\",\"name\":\"glocations\",\"value\":\"Virginia\"}],\"pub_date\":\"2015-04-19T00:00:00Z\",\"document_type\":\"article\",\"news_desk\":\"Magazine\",\"section_name\":\"Magazine\",\"subsection_name\":null,\"byline\":{\"contributor\":\"\",\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Sally\",\"rank\":1,\"lastname\":\"MANN\"}],\"original\":\"By SALLY MANN\"},\"type_of_material\":\"News\",\"_id\":\"552f965e38f0d824112d68dd\",\"word_count\":\"5166\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/2015\\/04\\/12\\/opinion\\/sunday\\/the-things-i-carried-back.html\",\"snippet\":\"From 40 years of reporting, a revulsion for ideology that smothers free thinking in both the totalitarian world and the West.\",\"lead_paragraph\":\"From 40 years of reporting, a revulsion for ideology that smothers free thinking in both the totalitarian world and the West.\",\"abstract\":\"John F Burns Dispatch column reflects on his 40-year career as foreign correspondent for The New York Times, on occasion of his retirement; explains that his time spent reporting on stories around the globe has left with him an enduring revulsion for ideology of any kind.\",\"print_page\":\"1\",\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-articleLarge.jpg\",\"height\":600,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-articleLarge.jpg\",\"xlargeheight\":\"600\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/12\\/opinion\\/sunday\\/12burns1\\/12burns1-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"The Things I Carried Back\",\"content_kicker\":\"Dispatch\",\"kicker\":\"Dispatch\",\"print_headline\":\"The Things I Carried Back\"},\"keywords\":[{\"rank\":\"1\",\"is_major\":\"Y\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"2\",\"is_major\":\"N\",\"name\":\"subject\",\"value\":\"News and News Media\"},{\"rank\":\"3\",\"is_major\":\"N\",\"name\":\"organizations\",\"value\":\"Taliban\"},{\"rank\":\"4\",\"is_major\":\"N\",\"name\":\"glocations\",\"value\":\"South Africa\"},{\"rank\":\"5\",\"is_major\":\"N\",\"name\":\"glocations\",\"value\":\"Afghanistan\"},{\"rank\":\"6\",\"is_major\":\"N\",\"name\":\"persons\",\"value\":\"Kim Il-sung\"},{\"rank\":\"7\",\"is_major\":\"N\",\"name\":\"subject\",\"value\":\"Apartheid (Policy)\"}],\"pub_date\":\"2015-04-12T00:00:00Z\",\"document_type\":\"article\",\"news_desk\":\"OpEd\",\"section_name\":\"Opinion\",\"subsection_name\":\"Sunday Review\",\"byline\":{\"contributor\":\"\",\"person\":[{\"firstname\":\"John\",\"middlename\":\"F.\",\"lastname\":\"BURNS\",\"rank\":1,\"role\":\"reported\",\"organization\":\"\"}],\"original\":\"By JOHN F. BURNS\"},\"type_of_material\":\"Op-Ed\",\"_id\":\"5529683438f0d81a55c4e927\",\"word_count\":\"1554\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/24\\/1905-times-building-is-the-tallest-in-the-city-with-a-big-asterisk\\/\",\"snippet\":\"David W. Dunlap looks back at The Times\\u2019s claim that its headquarters was, at one point, the tallest building in New York City.\",\"lead_paragraph\":null,\"abstract\":\"David W. Dunlap looks back at The Times\\u2019s claim that its headquarters was, at one point, the tallest building in New York City.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-articleLarge.jpg\",\"height\":1224,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-articleLarge.jpg\",\"xlargeheight\":\"1224\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/23\\/blogs\\/tbt1\\/tbt1-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"1905 | Times Building Is the Tallest in the City (With a Big Asterisk)\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Dunlap, David W\"},{\"rank\":\"1\",\"name\":\"glocations\",\"value\":\"City Hall Park (Manhattan, NY)\"},{\"rank\":\"2\",\"name\":\"glocations\",\"value\":\"New York City\"},{\"rank\":\"3\",\"name\":\"glocations\",\"value\":\"Park Row (Manhattan, NY)\"},{\"rank\":\"4\",\"name\":\"glocations\",\"value\":\"Times Square and 42nd Street (Manhattan, NY)\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"1 World Trade Center (Manhattan, NY)\"},{\"rank\":\"2\",\"name\":\"subject\",\"value\":\"Basements and Cellars\"},{\"rank\":\"3\",\"name\":\"subject\",\"value\":\"Buildings (Structures)\"},{\"rank\":\"4\",\"name\":\"subject\",\"value\":\"Historic Buildings and Sites\"}],\"pub_date\":\"2015-04-24T05:45:54Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"firstname\":\"David\",\"middlename\":\"W.\",\"lastname\":\"DUNLAP\",\"rank\":1,\"role\":\"reported\",\"organization\":\"\"}],\"original\":\"By DAVID W. DUNLAP\"},\"type_of_material\":\"Blog\",\"_id\":\"553a112038f0d87fad3b3bee\",\"word_count\":\"355\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/2015\\/04\\/18\\/business\\/new-york-times-co-elevates-meredith-kopit-levien-to-chief-revenue-officer.html\",\"snippet\":\"The executive vice president for advertising will now oversee revenue generation from all advertising and subscriptions.\",\"lead_paragraph\":\"The executive vice president for advertising will now oversee revenue generation from all advertising and subscriptions.\",\"abstract\":\"New York Times Company chief executive Mark Thompson says in memo to employees that executive vice president for advertising Meredith Kopit Levien has been promoted to chief revenue officer.\",\"print_page\":\"6\",\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-articleLarge.jpg\",\"height\":745,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-articleLarge.jpg\",\"xlargeheight\":\"745\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/18\\/business\\/18times-web\\/18times-web-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"New York Times Co. Elevates Meredith Kopit Levien to Chief Revenue Officer\",\"print_headline\":\"Times Company Appoints a Chief Revenue Officer\"},\"keywords\":[{\"rank\":\"1\",\"is_major\":\"Y\",\"name\":\"organizations\",\"value\":\"New York Times Company\"},{\"rank\":\"2\",\"is_major\":\"Y\",\"name\":\"persons\",\"value\":\"Kopit Levien, Meredith\"},{\"rank\":\"3\",\"is_major\":\"N\",\"name\":\"persons\",\"value\":\"Thompson, Mark John (1957- )\"},{\"rank\":\"4\",\"is_major\":\"N\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"5\",\"is_major\":\"N\",\"name\":\"subject\",\"value\":\"Appointments and Executive Changes\"}],\"pub_date\":\"2015-04-18T00:00:00Z\",\"document_type\":\"article\",\"news_desk\":\"Business\",\"section_name\":\"Business Day\",\"subsection_name\":\"Media\",\"byline\":{\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Ravi\",\"rank\":1,\"lastname\":\"SOMAIYA\"}],\"original\":\"By RAVI SOMAIYA\"},\"type_of_material\":\"News\",\"_id\":\"55310ae238f0d847eef0f7b0\",\"word_count\":\"377\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/21\\/a-lesson-from-sally-mann-just-take-the-picture\\/\",\"snippet\":\"Leslye Davis, who works in The Times\\u2019s video department, describes working with the legendary photographer Sally Mann on the video and photos that accompanied an excerpt from Ms. Mann\\u2019s book in The Times Magazine.\",\"lead_paragraph\":null,\"abstract\":\"Leslye Davis, who works in The Times\\u2019s video department, describes working with the legendary photographer Sally Mann on the video and photos that accompanied an excerpt from Ms. Mann\\u2019s book in The Times Magazine.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-articleLarge.jpg\",\"height\":417,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-articleLarge.jpg\",\"xlargeheight\":\"417\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/20\\/blogs\\/sallymann\\/sallymann-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"A Lesson From Sally Mann: &#8216;Just Take the Picture&#8217;\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Mann, Sally\"},{\"rank\":\"1\",\"name\":\"glocations\",\"value\":\"Virginia\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"Cameras\"}],\"pub_date\":\"2015-04-21T05:45:56Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Leslye\",\"rank\":1,\"lastname\":\"DAVIS\"}],\"original\":\"By LESLYE DAVIS\"},\"type_of_material\":\"Blog\",\"_id\":\"55361c6e38f0d821accecf62\",\"word_count\":\"508\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/16\\/reading-the-times-with-andy-borowitz\\/\",\"snippet\":\"The political satirist Andy Borowitz reads The New York Times on April 16, 2015.\",\"lead_paragraph\":null,\"abstract\":\"The political satirist Andy Borowitz reads The New York Times on April 16, 2015.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/16\\/blogs\\/borowitz\\/borowitz-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/16\\/blogs\\/borowitz\\/borowitz-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/16\\/blogs\\/borowitz\\/borowitz-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/16\\/blogs\\/borowitz\\/borowitz-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"Reading The Times With Andy Borowitz\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Borowitz, Andy\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"}],\"pub_date\":\"2015-04-16T17:08:23Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Susan\",\"rank\":1,\"lastname\":\"LEHMAN\"}],\"original\":\"By SUSAN LEHMAN\"},\"type_of_material\":\"Blog\",\"_id\":\"5530253138f0d847eef0f51f\",\"word_count\":\"743\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/20\\/heres-your-pulitzer-bad-news-to-come\\/\",\"snippet\":\"David W. Dunlap takes a look back at The Times\\u2019s Pulitzer history in honor of Monday\\u2019s awards announcements.\",\"lead_paragraph\":null,\"abstract\":\"David W. Dunlap takes a look back at The Times\\u2019s Pulitzer history in honor of Monday\\u2019s awards announcements.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-articleLarge.jpg\",\"height\":400,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-articleLarge.jpg\",\"xlargeheight\":\"400\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/20\\/blogs\\/pulitizer6\\/pulitizer6-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"Here&#8217;s Your Pulitzer. Bad News to Come.\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Baquet, Dean\"},{\"rank\":\"11\",\"name\":\"persons\",\"value\":\"Stalin, Joseph\"},{\"rank\":\"2\",\"name\":\"persons\",\"value\":\"Blair, Jayson\"},{\"rank\":\"3\",\"name\":\"persons\",\"value\":\"Carter, Kevin\"},{\"rank\":\"4\",\"name\":\"persons\",\"value\":\"Dunlap, David W\"},{\"rank\":\"5\",\"name\":\"persons\",\"value\":\"Duranty, Walter\"},{\"rank\":\"6\",\"name\":\"persons\",\"value\":\"Huxtable, Ada Louise\"},{\"rank\":\"7\",\"name\":\"persons\",\"value\":\"Keller, Bill\"},{\"rank\":\"8\",\"name\":\"persons\",\"value\":\"McCormick, Anne O'Hare\"},{\"rank\":\"9\",\"name\":\"persons\",\"value\":\"Raines, Howell\"},{\"rank\":\"10\",\"name\":\"persons\",\"value\":\"Robertson, Nan\"},{\"rank\":\"1\",\"name\":\"glocations\",\"value\":\"Africa\"},{\"rank\":\"2\",\"name\":\"glocations\",\"value\":\"Boston (Mass)\"},{\"rank\":\"3\",\"name\":\"glocations\",\"value\":\"Europe\"},{\"rank\":\"4\",\"name\":\"glocations\",\"value\":\"Johannesburg (South Africa)\"},{\"rank\":\"5\",\"name\":\"glocations\",\"value\":\"Sudan\"},{\"rank\":\"6\",\"name\":\"glocations\",\"value\":\"Ukraine\"},{\"rank\":\"7\",\"name\":\"glocations\",\"value\":\"USSR (Former Soviet Union)\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"Awards, Decorations and Honors\"},{\"rank\":\"2\",\"name\":\"subject\",\"value\":\"Deaths (Fatalities)\"},{\"rank\":\"3\",\"name\":\"subject\",\"value\":\"Freedom of the Press\"},{\"rank\":\"4\",\"name\":\"subject\",\"value\":\"News Sources, Confidential Status of\"},{\"rank\":\"5\",\"name\":\"subject\",\"value\":\"Newspapers\"},{\"rank\":\"6\",\"name\":\"subject\",\"value\":\"Photography\"},{\"rank\":\"7\",\"name\":\"subject\",\"value\":\"Plagiarism\"},{\"rank\":\"8\",\"name\":\"subject\",\"value\":\"Pulitzer Prizes\"},{\"rank\":\"9\",\"name\":\"subject\",\"value\":\"War Crimes, Genocide and Crimes Against Humanity\"}],\"pub_date\":\"2015-04-20T18:05:30Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"firstname\":\"David\",\"middlename\":\"W.\",\"lastname\":\"DUNLAP\",\"rank\":1,\"role\":\"reported\",\"organization\":\"\"}],\"original\":\"By DAVID W. DUNLAP\"},\"type_of_material\":\"Blog\",\"_id\":\"5535789d38f0d821accecdee\",\"word_count\":\"757\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/16\\/tune-in-to-the-times-14\\/\",\"snippet\":\"Mary Anne Weaver joined MSNBC\\u2019s \\u201cMorning Joe\\u201d to discuss her in-depth cover story on why middle-class British Muslims are joining Islamist militant groups so rapidly.\",\"lead_paragraph\":null,\"abstract\":\"Mary Anne Weaver joined MSNBC\\u2019s \\u201cMorning Joe\\u201d to discuss her in-depth cover story on why middle-class British Muslims are joining Islamist militant groups so rapidly.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-thumbWide-v4.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-thumbWide-v4.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-articleLarge-v3.jpg\",\"height\":97,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-articleLarge-v3.jpg\",\"xlargeheight\":\"97\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-thumbStandard-v4.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2014\\/09\\/18\\/blogs\\/tune\\/tune-thumbStandard-v4.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"Tune In to The Times\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Weaver, Mary Anne\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"MSNBC\"},{\"rank\":\"2\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"Muslims and Islam\"}],\"pub_date\":\"2015-04-16T10:53:48Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Jordan\",\"rank\":1,\"lastname\":\"COHEN\"}],\"original\":\"By JORDAN COHEN\"},\"type_of_material\":\"Blog\",\"_id\":\"552fcd6b38f0d824112d69a6\",\"word_count\":\"34\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/17\\/1984-behind-the-scenes-at-a-long-ago-magazine\\/\",\"snippet\":\"David W. Dunlap looks back at a promotional video, created by The New York Times Magazine to entice advertisers, and the star editors at The Times who made cameos in the clip.\",\"lead_paragraph\":null,\"abstract\":\"David W. Dunlap looks back at a promotional video, created by The New York Times Magazine to entice advertisers, and the star editors at The Times who made cameos in the clip.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-thumbWide.png\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-thumbWide.png\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-articleLarge.png\",\"height\":430,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-articleLarge.png\",\"xlargeheight\":\"430\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-thumbStandard.png\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/16\\/blogs\\/magazine3\\/magazine3-thumbStandard.png\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"1984 | Behind the Scenes at a Long-Ago Magazine\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"persons\",\"value\":\"Baker, Russell\"},{\"rank\":\"2\",\"name\":\"persons\",\"value\":\"Claiborne, Craig\"},{\"rank\":\"3\",\"name\":\"persons\",\"value\":\"Donovan, Carrie\"},{\"rank\":\"4\",\"name\":\"persons\",\"value\":\"Dunlap, David W\"},{\"rank\":\"5\",\"name\":\"persons\",\"value\":\"Gelb, Arthur\"},{\"rank\":\"6\",\"name\":\"persons\",\"value\":\"Robertson, Nan\"},{\"rank\":\"7\",\"name\":\"persons\",\"value\":\"Rosenthal, A M\"},{\"rank\":\"8\",\"name\":\"persons\",\"value\":\"Shugrue, Martin R Jr\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"Airlines and Airplanes\"},{\"rank\":\"2\",\"name\":\"subject\",\"value\":\"Museums\"},{\"rank\":\"3\",\"name\":\"subject\",\"value\":\"Recording Equipment\"}],\"pub_date\":\"2015-04-17T10:20:52Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"firstname\":\"David\",\"middlename\":\"W.\",\"lastname\":\"DUNLAP\",\"rank\":1,\"role\":\"reported\",\"organization\":\"\"}],\"original\":\"By DAVID W. DUNLAP\"},\"type_of_material\":\"Blog\",\"_id\":\"553116ef38f0d847eef0f7e0\",\"word_count\":\"490\"},{\"web_url\":\"http:\\/\\/www.nytimes.com\\/times-insider\\/2015\\/04\\/15\\/a-look-back-through-the-times-photo-archives\\/\",\"snippet\":\"Darcy Eveleigh, the photo editor of the Lively Morgue blog, shares its 500th post and a selection of other photos that have been chosen from the Times archives.\",\"lead_paragraph\":null,\"abstract\":\"Darcy Eveleigh, the photo editor of the Lively Morgue blog, shares its 500th post and a selection of other photos that have been chosen from the Times archives.\",\"print_page\":null,\"blog\":[],\"source\":\"The New York Times\",\"multimedia\":[{\"width\":190,\"url\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-thumbWide.jpg\",\"height\":126,\"subtype\":\"wide\",\"legacy\":{\"wide\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-thumbWide.jpg\",\"wideheight\":\"126\",\"widewidth\":\"190\"},\"type\":\"image\"},{\"width\":600,\"url\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-articleLarge.jpg\",\"height\":223,\"subtype\":\"xlarge\",\"legacy\":{\"xlargewidth\":\"600\",\"xlarge\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-articleLarge.jpg\",\"xlargeheight\":\"223\"},\"type\":\"image\"},{\"width\":75,\"url\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-thumbStandard.jpg\",\"height\":75,\"subtype\":\"thumbnail\",\"legacy\":{\"thumbnailheight\":\"75\",\"thumbnail\":\"images\\/2015\\/04\\/15\\/upshot\\/15Lively-combo\\/15Lively-combo-thumbStandard.jpg\",\"thumbnailwidth\":\"75\"},\"type\":\"image\"}],\"headline\":{\"main\":\"A Look Back Through the Times Photo Archives\",\"kicker\":\"Times Insider\"},\"keywords\":[{\"rank\":\"1\",\"name\":\"glocations\",\"value\":\"Bronx (NYC)\"},{\"rank\":\"2\",\"name\":\"glocations\",\"value\":\"France\"},{\"rank\":\"1\",\"name\":\"organizations\",\"value\":\"New York Times\"},{\"rank\":\"2\",\"name\":\"organizations\",\"value\":\"Tumblr\"},{\"rank\":\"1\",\"name\":\"subject\",\"value\":\"Archives and Records\"},{\"rank\":\"2\",\"name\":\"subject\",\"value\":\"News and News Media\"},{\"rank\":\"3\",\"name\":\"subject\",\"value\":\"Photography\"}],\"pub_date\":\"2015-04-15T16:30:01Z\",\"document_type\":\"blogpost\",\"news_desk\":null,\"section_name\":\"false\",\"subsection_name\":\"false\",\"byline\":{\"person\":[{\"organization\":\"\",\"role\":\"reported\",\"firstname\":\"Darcy\",\"rank\":1,\"lastname\":\"EVELEIGH\"}],\"original\":\"By DARCY EVELEIGH\"},\"type_of_material\":\"Blog\",\"_id\":\"552ecaaa38f0d824112d6684\",\"word_count\":\"352\"}]},\"status\":\"OK\",\"copyright\":\"Copyright (c) 2013 The New York Times Company.  All Rights Reserved.\"}";
        }



        try{

            ContentFragment testFragment=((ContentFragment)this.fragmentContext);

            JSONObject jsonObject=new JSONObject(s);
            JSONArray resultArray=jsonObject.getJSONObject("response").getJSONArray("docs");

            //for(JSONObject obj:resultArray){


            testFragment.adpList.clear();
            testFragment.detailList.clear();
            for(int i=0;i<resultArray.length();i++){

                String line=((JSONObject)resultArray.get(i)).getJSONObject("headline").getString("main");

                testFragment.adpList.add(line);
                testFragment.detailList.add((JSONObject)resultArray.get(i));
                testFragment.adp.notifyDataSetChanged();
                Log.d("REsult!",line);

            }




               // Log.d("REsult!",resultArray.get().toString());
            //}


        }

        catch(Exception e){

            //downcast failed!

            Log.e("richard!!!!downcast failed!",e.toString());

        }

    }
}



