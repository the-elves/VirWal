package com.example.illuminati.virwall;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


public class Data {

    private final String USER_AGENT = "Mozilla/5.0";
    public String my_response;
    public static void main(String[] args) throws Exception {

        Data http = new Data();
        String[] list={"ipod","pepsi", "chips", "mobile", "pillow", "clock", "deo","tv","book","chair"};
        Map m1=new HashMap();

        Number[] recolist = {0,0,0,0,0,0,0,0,0,0};
        for(int i=0;i<10;i++){
            String my_response=http.searchItem(list[i]);
            Number temp=http.giveItemID(my_response,1);
            m1.put(temp,list[i]);
//                http.giveReviews(temp);
            recolist[i]=http.giveReco(temp);
        }
        Arrays.sort(recolist);
        System.out.println(recolist[5]);
        m1.put(recolist[5], http.giveProduct(recolist[5]));

        Map mymap = new TreeMap(m1);
        Set set2 = mymap.entrySet();
        Iterator iterator2 = set2.iterator();
        while(iterator2.hasNext()) {
            Map.Entry me2 = (Map.Entry)iterator2.next();
            System.out.print(me2.getKey() + ": ");
            System.out.println(me2.getValue());
        }
    }


    private String searchItem(String s) throws Exception {

        String url = "http://api.walmartlabs.com/v1/search?apiKey=wdferpzyb95839q6py86nhfv&query="+ s;
        return sendGet(url);

    }



    private Number giveItemID(String s, int p) throws Exception{
//                System.out.println("=======decode=======");
        Number id = null;
        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };

        try{
            JSONObject m = (JSONObject) parser.parse(s);
            JSONArray inner = (JSONArray) m.get("items");
            //System.out.println("\n\nqweqwe" + inner.get(0));
            JSONObject obj = (JSONObject) inner.get(0);
            id= (Number) obj.get("itemId");
                /*
                 * {"itemId":37615434,"parentItemId":37615434,
                 * "name":"Apple iPod touch 16GB","msrp":189.0,"salePrice":159.99,"upc":"885909958979","categoryPath":"Electronics/Portable Audio/All MP3 Players",
                 * "shortDescription":"iPod touch is more fun than ever. It has an ultrathin design, a 4-inch Retina display, a 5MP iSight camera, iOS 7, Siri, iMessage, FaceTime, iTunes and the App Store, iTunes Radio, and more.","longDescription":"&lt;br&gt;&lt;b&gt;Key Features:&lt;/b&gt;&lt;ul&gt;&lt;li&gt;Ultrathin design available in five gorgeous colors&lt;/li&gt;&lt;li&gt;4-inch Retina display&lt;/li&gt;&lt;li&gt;Apple A5 chip&lt;/li&gt;&lt;li&gt;5-megapixel iSight camera with 1080p HD video recording&lt;/li&gt;&lt;li&gt;FaceTime HD camera with 1.2-megapixel photos
                 *  and 720p HD video recording&lt;/li&gt;&lt;li&gt;iOS 7 with features like Control Center, AirDrop, smarter multitasking, and iTunes Radio&lt;/li&gt;&lt;li&gt;Siri intelligent assistant&lt;/li&gt;&lt;li&gt;iTunes Store with millions of songs, and thousands of movies and TV shows&lt;/li&gt;&lt;li&gt;App Store with more than 1,000,000 apps, including over 100,000 games&lt;sup&gt;1&lt;/sup&gt;&lt;/li&gt;&lt;li&gt;Game Center with millions of gamers&lt;/li&gt;&lt;li&gt;Free text messaging over Wi-Fi with iMessage&lt;/li&gt;&lt;li&gt;Rich HTML email and Safari web browser&lt;/li&gt;&lt;li&gt;AirPlay and AirPlay Mirroring&lt;/li&gt;&lt;li&gt;40 hours of music playback, 8 hours of video playback&lt;sup&gt;2&lt;/sup&gt;&lt;/li&gt;&lt;li&gt;iPod touch loop (sold separately for 16GB model)&lt;/li&gt;&lt;li&gt;Apple EarPods&lt;/li&gt;&lt;li&gt;16GB, 32GB, and 64GB capacities&lt;sup&gt;3&lt;/sup&gt;&lt;/li&gt;&lt;/ul&gt;&lt;br&gt;iPod models are not available in all colors at all resellers. iPod touch loop is included with 32GB and 64GB models only. Wi-Fi Internet access is required for some features; broadband recommended; fees may apply. Some features, applications, and services are not available in all areas. Application availability and pricing are subject to change. &lt;br&gt;&lt;br&gt;&lt;sup&gt;1&lt;/sup&gt;App count refers to the total number of apps worldwide. Not all content is available in all countries. &lt;br&gt;&lt;sup&gt;2&lt;/sup&gt;Rechargeable batteries have a limited number of charge cycles and may eventually need to be replaced. Battery life and number of charge cycles vary by use and settings. See www.apple.com/batteries for more information. &lt;br&gt;&lt;sup&gt;3&lt;/sup&gt;1GB = 1 billion bytes; actual formatted capacity less.
                 *  Accessories and Related Products&lt;ul class=&quot;noindent&quot;&gt;&lt;li&gt;Apple EarPods with Remote and Mic&lt;/li&gt;&lt;li&gt;Apple Lightning to USB Cable&lt;/li&gt;&lt;li&gt;Apple Lightning to 30-pin Adapter &lt;/li&gt;&lt;li&gt;Apple Lightning to 30-pin Adapter (0.2 m) &lt;/li&gt;&lt;li&gt;AppleCare+ for iPod touch/iPod classic?&lt;/li&gt;&lt;/ul&gt;","thumbnailImage":"http://i.walmartimages.com/i/p/00/88/59/09/95/0088590995897_Color_Silver_SW_100X100.jpg","productTrackingUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Fwww.walmart.com%252Fip%252FiPod-Touch-16GB-Assorted-Colors%252F37615434%253Faffp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","standardShipRate":0.0,"marketplace":false,"modelNumber":"MGG52LL/A","productUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Fwww.walmart.com%2Fip%2FiPod-Touch-16GB-Assorted-Colors%2F37615434%3Faffp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi","customerRating":"4.627","numReviews":367,"customerRatingImage":"http://i2.walmartimages.com/i/CustRating/4_6.gif","categoryNode":"3944_96469_164001","bundle":false,"addToCartUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D37615434%7C1%26affp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi","affiliateAddToCartUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D37615434%257C1%2526affp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","availableOnline":true},{"itemId":21805460,"parentItemId":21805460,"name":"Apple iPod shuffle 2GB","msrp":46.0,"salePrice":39.0,"upc":"885909611768","categoryPath":"Electronics/Portable Audio/All MP3 Players","shortDescription":"The incredibly small, wearable music player comes in a spectrum of colors and has a clickable control pad. And VoiceOver tells you the song title or playlist name.","longDescription":"Take hundreds of songs everywhere you go with the small, wearable and colourful iPod shuffle. Use the clickable control pad to easily play your music. With a simple tap of the VoiceOver button, you'll hear the song title, playlist name or even battery status. And because your different playlists and Genius Mixes are just a sync away, you'll always have the perfect music mix ready to complement your every mood.&lt;p&gt;Key Features&lt;/p&gt;&lt;p&gt;&lt;/p&gt;&lt;ul class=&quot;noindent&quot;&gt;&lt;li&gt;Small and wearable&lt;/li&gt;&lt;li&gt;Polished anodised aluminium design&lt;/li&gt;&lt;li&gt;Built-in clip&lt;/li&gt;&lt;li&gt;Easy-to-use control pad for playing your music, audiobooks and podcasts&lt;/li&gt;&lt;li&gt;VoiceOver button to hear the song title, playlist menu and battery status&lt;/li&gt;&lt;li&gt;Support for multiple playlists and Genius Mixes&lt;/li&gt;&lt;li&gt;2GB of storage&lt;/li&gt;&lt;li&gt;Available in slate, silver, purple, pink, yellow, green and blue&lt;/li&gt;&lt;li&gt;Apple iPod shuffle USB Cable&lt;/li&gt;&lt;li&gt;Apple Earphones&lt;/li&gt;&lt;li&gt;Works with Mac and PC&quot;&lt;/li&gt;&lt;/ul&gt;","thumbnailImage":"http://i.walmartimages.com/i/p/00/88/59/09/61/0088590961176_Color_Pink_SW_100X100.jpg","productTrackingUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Fwww.walmart.com%252Fip%252FApple-iPod-shuffle-2GB%252F21805460%253Faffp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","standardShipRate":4.97,"marketplace":false,"modelNumber":"MD773LL/A","productUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Fwww.walmart.com%2Fip%2FApple-iPod-shuffle-2GB%2F21805460%3Faffp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi","customerRating":"4.397","numReviews":559,"customerRatingImage":"http://i2.walmartimages.com/i/CustRating/4_4.gif","categoryNode":"3944_96469_164001","bundle":false,"stock":"Available","addToCartUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D21805460%7C1%26affp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi","affiliateAddToCartUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D21805460%257C1%2526affp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","availableOnline":true},{"itemId":21805451,"parentItemId":21805451,"name":"Apple iPod nano 16GB","msrp":145.0,"salePrice":134.99,"upc":"885909565078","categoryPath":"Electronics/Portable Audio/All MP3 Players","shortDescription":"The redesigned, ultraportable iPod nano has a larger, 2.5-inch Multi-Touch display; plays music, FM radio and videos; includes built-in Bluetooth technology; and features a pedometer and Nike+ support for fitness.","longDescription":"The redesigned, ultraportable iPod nano now has a larger 2.5-inch Multi- Touch display. Play your favourite songs, browse music by genre, or listen to Genius playlists and FM radio. Or watch movies and widescreen videos on the bigger screen. A perfect workout partner, iPod nano tracks your steps, your runs and burned calories and syncs to the Nike+ website to challenge friends. And with built-in Bluetooth technology, you can wirelessly connect to speakers, headphones or car stereos.Key Features&lt;p&gt;&lt;/p&gt;&lt;ul class=&quot;noindent&quot;&gt;&lt;li&gt;2.5-inch Multi-Touch colour display with 240-by-432-pixel resolution&lt;/li&gt;&lt;li&gt;Only 5.4 millimetres thin &mdash; the thinnest iPod ever&lt;/li&gt;&lt;li&gt;Easy-to-use controls to quickly adjust volume, or play, pause and change songs&lt;/li&gt;&lt;li&gt;Bluetooth 4.0&lt;/li&gt;&lt;li&gt;Widescreen video&lt;/li&gt;&lt;li&gt;FM radio with Live Pause&lt;/li&gt;&lt;li&gt;Built-in pedometer for fitness&lt;/li&gt;&lt;li&gt;Built-in Nike+ support for voice feedback and syncing to nikeplus.com&lt;/li&gt;&lt;li&gt;Anodised aluminium in seven gorgeous colors&lt;/li&gt;&lt;li&gt;16GB capacity&lt;/li&gt;&lt;li&gt;Up to 30 hours of music playback&lt;/li&gt;&lt;li&gt;Apple EarPods&lt;/li&gt;&lt;li&gt;Works with Mac and PC&lt;/li&gt;&lt;/ul&gt;","thumbnailImage":"http://i.walmartimages.com/i/p/00/88/59/09/56/0088590956507_Color_Green_SW_100X100.jpg","productTrackingUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Fwww.walmart.com%252Fip%252FiPod-nano-16GB%252F21805451%253Faffp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","standardShipRate":0.0,"marketplace":false,"modelNumber":"MD478LL/A","productUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Fwww.walmart.com%2Fip%2FiPod-nano-16GB%2F21805451%3Faffp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi",
                 *  "customerRating":"4.427","numReviews":742,"customerRatingImage":"http://i2.walmartimages.com/i/CustRating/4_4.gif","categoryNode":"3944_96469_164001","bundle":false,"addToCartUrl":"http://c.affil.walmart.com/t/api02?l=http%3A%2F%2Faffil.walmart.com%2Fcart%2FaddToCart%3Fitems%3D21805451%7C1%26affp1%3D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%26affilsrc%3Dapi%26veh%3Daff%26wmlspartner%3Dreadonlyapi","affiliateAddToCartUrl":"http://linksynergy.walmart.com/fs-bin/click?id=|LSNID|&offerid=223073.7200&type=14&catid=8&subid=0&hid=7200&tmpid=1082&RD_PARM1=http%253A%252F%252Faffil.walmart.com%252Fcart%252FaddToCart%253Fitems%253D21805451%257C1%2526affp1%253D-TEcmJR2gciVTjcTk8c7Ysh4MZr9wWmftu4UVr2uZeA%2526affilsrc%253Dapi","availableOnline":true}
                 * */
            if(p==0){
                System.out.println("Customer Rating: " + obj.get("customerRating"));
                System.out.println("Number of reviews:" + obj.get("numReviews"));
            }

        }
        catch(ParseException pe){
            pe.printStackTrace();
        }
        return id;
    }


    private void giveReviews(Number t) throws Exception{
        String url = "http://api.walmartlabs.com/v1/reviews/"+t+"?apiKey=wdferpzyb95839q6py86nhfv&format=json";
        String s=sendGet(url);
//            System.out.println("=======decode=======");
        Number id = null;
        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };

        try{
            JSONObject m = (JSONObject) parser.parse(s);
            JSONArray inner = (JSONArray) m.get("reviews");
            //System.out.println("\n\nqweqwe" + inner.get(0));
            JSONObject obj = (JSONObject) inner.get(0);
            System.out.println("Review: "+ obj.get("reviewText"));
            System.out.println("Upvotes:"+obj.get("upVotes"));
            System.out.println("Downvotes:"+obj.get("downVotes"));

        }
        catch(ParseException pe){
            pe.printStackTrace();
        }
//            return id;
    }

    private Number giveReco(Number t) throws Exception{
        String url = "http://api.walmartlabs.com/v1/nbp?apiKey=wdferpzyb95839q6py86nhfv&format=json&itemId="+t;
        String s=sendGet(url);
        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };


        Object obj=JSONValue.parse(s);
        JSONArray array = (JSONArray) obj;
        JSONObject obj2=(JSONObject)array.get(1);
        System.out.println("Recommended Product");
        System.out.println(obj2.get("name"));
        System.out.println("ID" + obj2.get("itemId"));

//                  JSONObject inner=(JSONObject) array.get(0);
//
//                  JSONObject m = (JSONObject) ;
//                  JSONArray myinner = (JSONArray) m.get("name");
        //System.out.println("\n\nqweqwe" + inner.get(0));
//                  JSONObject obj = (JSONObject) inner.get(0);
//                  JSONArray myinner=(JSONArray) inner.get("name");
//                  JSONObject myinner = (JSONObject) inner.get("name");
//                  System.out.println("Reccomend: "+ myinner);
//                  System.out.println("Upvotes:"+obj.get("upVotes"));
//                  System.out.println("Downvotes:"+obj.get("downVotes"));
        return (Number) obj2.get("itemId");

    }

    private Object giveProduct(Number t) throws Exception{
        String url = "http://api.walmartlabs.com/v1/items/"+t+"?apiKey=wdferpzyb95839q6py86nhfv&format=json";
        String s=sendGet(url);
        JSONParser parser = new JSONParser();
        ContainerFactory containerFactory = new ContainerFactory(){
            public List creatArrayContainer() {
                return new LinkedList();
            }

            public Map createObjectContainer() {
                return new LinkedHashMap();
            }

        };


        JSONObject m = (JSONObject) parser.parse(s);
        return m.get("name");
        //            return id;
    }

    // HTTP GET request
    private String sendGet(String s) throws Exception {

        //String url = "http://api.walmartlabs.com/v1/nbp?apiKey=wdferpzyb95839q6py86nhfv&itemId=35609147";
        String url=s;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
//                System.out.println("\nSending 'GET' request to URL : " + url);
//                System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        //System.out.println(response.toString());
        return response.toString();
    }}