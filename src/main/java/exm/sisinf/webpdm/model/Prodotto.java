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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(schema = "public", name = "prodotti_pkey", sequenceName = "prodotti_pkey", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "prezzo_kg")
    private Double prezzoKg;

    @Column(name = "data_arrivo")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataArrivo;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "immagine")
    private String immagine;

    @ManyToMany
    @JoinTable(
            name = "acquisti",
            joinColumns = @JoinColumn(name = "prodotto_id"),
            inverseJoinColumns = @JoinColumn(name = "vendita_id")
    )
    private Collection<Vendita> vendite;

    public Prodotto(String nome, Double prezzoKg, Date dataArrivo, Integer quantita, String descrizione, String immagine) {
        this.nome = nome;
        this.prezzoKg = prezzoKg;
        this.dataArrivo = dataArrivo;
        this.quantita = quantita;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }
}
