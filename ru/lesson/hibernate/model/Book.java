package ru.lesson.hibernate.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Books")
public class Book {
    private Long id;
    private String name;
    private String description;
    private Date publishDat;
    private Double price;
    private List<Author> listAuthor;
    private Publisher publisher;

    public Book() {

    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "book_entity_seq_gen")
    @SequenceGenerator(name = "book_entity_seq_gen", sequenceName = "book_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publishedat")
    public Date getPublishDat() {
        return publishDat;
    }

    public void setPublishDat(Date publishDate) {
        this.publishDat = publishDat;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "booksToAuthors",
            joinColumns = @JoinColumn(name = "IDBOOK"),
            inverseJoinColumns = @JoinColumn(name = "idAuthor"))
    public List<Author> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(List<Author> listAuthor) {
        this.listAuthor = listAuthor;
    }
    @ManyToOne
    @JoinColumn(name ="idPublisher")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != null ? !id.equals(book.id) : book.id != null) return false;
        if (name != null ? !name.equals(book.name) : book.name != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (publishDat != null ? !publishDat.equals(book.publishDat) : book.publishDat != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (listAuthor != null ? !listAuthor.equals(book.listAuthor) : book.listAuthor != null) return false;
        return publisher != null ? publisher.equals(book.publisher) : book.publisher == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publishDat != null ? publishDat.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (listAuthor != null ? listAuthor.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", publishDat=" + publishDat +
                ", price=" + price +
                ", listAuthor=" + listAuthor +
                ", publisher=" + publisher +
                '}';
    }
}
