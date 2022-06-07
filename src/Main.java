import java.util.*;

public class Main {
    public static void main(String[] args)  {
        //Создание выборки входных данных
        Map<String, String> hm1 = new HashMap<>();
        Map<String, String> hm2 = new HashMap<>();
        filldata(1,hm1);
        filldata(2,hm2);
        //Вычисление
        String Result =calc(hm1, hm2);
        System.out.printf(Result);
    }

    static void filldata(int k, Map hm){
        for (int i=0; i <= 4+k; i+=k){
            hm.put(String.format("url%d",i), String.format("data%d",i%(6/k)));
        }
        printdata(k,hm);
    }

    static void printdata(int k, Map<String,String> hm){
        System.out.printf("hm%d\n",k);
        for(Map.Entry<String, String> item : hm.entrySet()){
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
    }

    static String calc(Map<String,String> hm1, Map<String,String> hm2){
        List <String> update  = new ArrayList<>();
        List <String> insert  = new ArrayList<>();
        List <String> del  = new ArrayList<>();
        Set key1 = hm1.keySet();
        Set key2 = hm2.keySet();
        find_upd_insert(hm1.keySet(), hm2.keySet(),update,insert,hm1,hm2);
        find_del(key1, key2,del);
        return print (convert(del), convert(insert),convert(update)) ;
    }

    static void find_upd_insert(Set keys1, Set keys2, List update, List insert, Map hm1, Map hm2){
            for (String current : (Iterable<String>) keys2) {
                if (!(keys1.contains(current))) {
                    insert.add(current);
                    continue;
                }
                if (!hm1.get(current).equals(hm2.get(current)))
                    update.add(current);
        }
    }
    static void find_del(Set keys1, Set keys2, List del){
        if (!keys1.equals(keys2))
            for (String current : (Iterable<String>) keys1)
                if (!(keys2.contains(current)))
                    del.add(current);
    }

    static String convert(List list){
        return String.join(",", list);
    }

    static String print (String del, String insert, String update){
        return String.format("Здравствуйте, дорогая и.о. секретаря\n" +
                "\n" +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                "\n" +
                "Исчезли следующие страницы: %s\n" +
                "Появились следующие новые страницы: %s\n" +
                "Изменились следующие страницы: %s\n" +
                "\n" +
                "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.",del, insert,update);
    }

}