package com.k2senterprise.codility.task2;

@Entity
@EqualsAndHashCode
public class Alert {
    @Id
    private UUID id;
    private String author;
    private String title;

    private Alert() {}

    public Alert(UUID id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }
}

