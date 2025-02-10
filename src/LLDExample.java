import java.util.*;

public class LLDExample {

/*
    Given an access log for a feature, count the number of repeat customers.
    In order to filter out the "novelty effect" a repeat customer is defined as a
    customer who has used the feature on more than one day.
    Each line of the access log is tab delimited with two fields:
    the timestamp of when the customer visited, and the customer id (a 10 byte string).
    The feature writes an entry to the log file as it gets the hits. Below is an example log file.

            11-Jun-2018 1:00 AM, 1ABCDEFGHI
11 JUN-2019
            11-Jun-2018 3:01 AM, 1ABCDEFGHI
11-Jun-2018 4:12 AM, 2ABCDEFGHI
12-Jun-2018 8:23 AM, 3ABCDEFGHI
12-Jun-2018 4:21 PM, 2ABCDEFGHI
13-Jun-2018 1:14 PM, 3ABCDEFGHI


    In this example, the repeat customers are "3ABCDEFGHI" and "2ABCDEFGHI".
    The result that your program generates is the count of repeat customers, in this case 2.

 */
    Map<String, List<Integer>> user_timestamp_map;
    public int parseDate(String timestamp){
        String[] time_fields = timestamp.split(" ");
        String date = time_fields[0];
        String[] date_fields = date.split("-");
        int parsed_date = Integer.parseInt(date_fields[0]);
        return parsed_date;
    }

    public Map<String, List<Integer>> buildMap( String user, int parsed_date){
        if(!user_timestamp_map.containsKey(user)) {
            List<Integer> list = new ArrayList<>();
            list.add(parsed_date);
            user_timestamp_map.put(user, list);
        }
        else {
            List<Integer>  list= user_timestamp_map.get(user);
            int latestUserTimestamp = list.get(list.size()-1);
            if(Math.abs(parsed_date - latestUserTimestamp) > 1){
                list.add(parsed_date);
                user_timestamp_map.put(user, list);
            }
        }
        return user_timestamp_map;
    }


    public List<String> getRepeatCustomers(List<String> log){
        user_timestamp_map = new HashMap<>();
        for(String line : log){
            String[] log_fields = line.split(", ");
            String timestamp = log_fields[0];
            int parsed_date = parseDate(timestamp);
            String user = log_fields[1];
           buildMap(user, parsed_date);
        }
        List<String> ans = buildResultList(user_timestamp_map);
        return  ans;
    }

    public List<String> buildResultList(Map<String, List<Integer>> map){
        List<String> ans = new ArrayList<>();
        for(String user : map.keySet()){
            if(map.get(user).size() > 1)
                ans.add(user);
        }
        return ans;
    }
}
