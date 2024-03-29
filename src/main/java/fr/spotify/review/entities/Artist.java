package fr.spotify.review.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.spotify.review.views.Views;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="artists")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.AlbumResponseView.class, Views.HistoricsView.class})
    @Column(name="id")
    private Long id;

    @Column(unique=true)

    @JsonView({Views.AlbumResponseView.class, Views.HistoricsView.class})
    private String name;

    @OneToMany(targetEntity = Album.class, mappedBy = "artist")
    @JsonView(Views.AlbumResponseView.class)
    List<Album> albums= new ArrayList<>();

    public Artist(String name) {
        this.name = name;
    }
    public Artist(Long id, String name) {
        this.id=id;
        this.name = name;
    }

    public boolean equals(Artist artist){
    if (this.getId()== artist.getId() && this.getName()==artist.getName()) return true;
    else return false;
    }
}
