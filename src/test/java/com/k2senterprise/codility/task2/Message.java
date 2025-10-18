package com.k2senterprise.codility.task2;

@Entity
@EqualsAndHashCode
public class Message {

    @Id
    private UUID id;
    @Column(unique = true)
    private String author;
    private String title;
    private String content;

    private Message() {}

    public Message(UUID id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}

