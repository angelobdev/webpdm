package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
public class Prodotto {

    @Id
    @GeneratedValue(generator = "prodotti_id_seq")
    @SequenceGenerator(name = "prodotti_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "prezzo_al_kg")
    private Double prezzoAlKg;

    @Column(name = "data_arrivo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataArrivo;

    @Column(name = "quantita_in_magazzino")
    private Integer quantitaInMagazzino;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "immagine")
    private String immagine;

    public Prodotto(String nome, Double prezzoAlKg, Date dataArrivo, Integer quantitaInMagazzino, String descrizione, String immagine) {
        this.nome = nome;
        this.prezzoAlKg = prezzoAlKg;
        this.dataArrivo = dataArrivo;
        this.quantitaInMagazzino = quantitaInMagazzino;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }
}
