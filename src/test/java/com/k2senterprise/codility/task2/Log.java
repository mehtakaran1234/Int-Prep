package com.k2senterprise.codility.task2;

@Entity
@EqualsAndHashCode
public class Log {
    @Id
    private UUID id;
    private String title;
    private String content;

    private Log() {}

    public Log(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

