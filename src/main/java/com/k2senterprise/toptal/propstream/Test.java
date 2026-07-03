package com.k2senterprise.toptal.propstream;

/*
We have a system that stores files.
Each file has a name, size, and may belong to a “collection” (think of collections as tags, not folders).

We want to generate a report that lists:

The total size of all files.

The top N collections (by total file size).*/

/*Example Input:
        [
        {"file": "file1.txt", "size": 100},
        {"file": "file2.txt", "size": 200, "collectionId": "collection1"},
        {"file": "file3.txt", "size": 200, "collectionId": "collection1"},
        {"file": "file4.txt", "size": 300, "collectionId": "collection2"},
        {"file": "file5.txt", "size": 10}
        ]*/
//810
//collection1: 400
//collection2 300
import java.util.*;
import java.util.stream.*;

/*
Utility to compute total storage and top N collections by total file size.
*/
public class Test {
    static class FileItem {
        final String file;
        final long size;
        final String collectionId;

        FileItem(String file, long size, String collectionId) {
            this.file = file;
            this.size = size;
            this.collectionId = collectionId;
        }
    }

    static class CollectionSum {
        final String id;
        final long size;

        CollectionSum(String id, long size) {
            this.id = id;
            this.size = size;
        }
    }

    static class Report {
        final long totalSize;
        final List<CollectionSum> topCollections;

        Report(long totalSize, List<CollectionSum> topCollections) {
            this.totalSize = totalSize;
            this.topCollections = Collections.unmodifiableList(topCollections);
        }
    }

    public static Report generateReport(List<FileItem> files, int topN) {
        long total = 0L;
        Map<String, Long> sums = new HashMap<>();
        for (FileItem f : files) {
            total += f.size;
            if (f.collectionId != null) {
                sums.merge(f.collectionId, f.size, Long::sum);
            }
        }
        List<CollectionSum> top = sums.entrySet().stream()
                .map(e -> new CollectionSum(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong((CollectionSum cs) -> cs.size).reversed())
                .limit(topN)
                .collect(Collectors.toList());
        return new Report(total, top);
    }

    public static void main(String[] args) {
        List<FileItem> sample = Arrays.asList(
                new FileItem("file1.txt", 100, null),
                new FileItem("file2.txt", 200, "collection1"),
                new FileItem("file3.txt", 200, "collection1"),
                new FileItem("file4.txt", 300, "collection2"),
                new FileItem("file5.txt", 10, null)
        );

        Report r = generateReport(sample, 2);
        System.out.println("Total size: " + r.totalSize);
        System.out.println("Top collections:");
        for (CollectionSum cs : r.topCollections) {
            System.out.println(cs.id + ": " + cs.size);
        }
    }
}

