package exm.sisinf.webpdm.model;

import exm.sisinf.webpdm.model.support.CarrelloProdotto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "quantita_stoccata")
    private Integer quantitaStoccata;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "immagine")
    private String immagine;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrelloProdotto> carrelloProdotti = new ArrayList<>();

    public Prodotto(String nome, Double prezzoAlKg, Date dataArrivo, Integer quantitaStoccata, String descrizione, String immagine) {
        this.nome = nome;
        this.prezzoAlKg = prezzoAlKg;
        this.dataArrivo = dataArrivo;
        this.quantitaStoccata = quantitaStoccata;
        this.descrizione = descrizione;
        this.immagine = immagine;
    }

    @Override
    public String toString() {
        return nome;
    }
}
