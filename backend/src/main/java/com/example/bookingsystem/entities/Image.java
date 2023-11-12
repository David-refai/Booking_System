package com.example.bookingsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Image implements java.io.Serializable {
    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "image_sequence")
    private Long id;

    // byte[] imageData; // If storing images as byte arrays

//    @Basic(fetch = FetchType.LAZY)
    @Lob
    @Column(name = "image_data", nullable = false)
    private byte[] imageData;
    private String ImageName; // If storing images as byte arrays// Other properties or relationships if needed

    // Getters, setters, constructors

    @ManyToOne( cascade ={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JsonIgnoreProperties("images")
    @JsonIgnore
    @JoinColumn(name = "trip_id")
    private Trip trip;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Image image = (Image) o;
        return getId() != null && Objects.equals(getId(), image.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
