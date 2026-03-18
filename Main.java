import engine.*;
import java.util.List;
import java.util.Scanner;
import model.FileRecord;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter directory to index: ");
        String root = sc.nextLine();

        FileIndexer indexer = new FileIndexer();
        indexer.indexDirectory(root);

        FileSearcher searcher = new FileSearcher(indexer);
        CacheManager cache = new CacheManager();

        new Thread(new FileWatcher(root)).start();

        while (true) {
            System.out.println("\n1. Search by name");
            System.out.println("2. Search by extension");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 3) break;

            System.out.print("Enter query: ");
            String query = sc.nextLine().toLowerCase();

            if (cache.contains(query)) {
                System.out.println("From cache:");
                cache.get(query).forEach(System.out::println);
                continue;
            }

            List<FileRecord> result = (choice == 1)
                    ? searcher.searchByName(query)
                    : searcher.searchByExtension(query);

            cache.put(query, result);

            if (result.isEmpty()) {
                System.out.println("No files found.");
            } else {
                result.forEach(System.out::println);
            }
        }

        sc.close();
    }
}
