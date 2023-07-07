package exm.sisinf.webpdm.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

@Entity
@Table(name = "prodotti")
@Data
@NoArgsConstructor
public class Prodotto {

    // COMPARATORS
    public static Comparator<Prodotto> SORT_BY_NAME_ASC = (o1, o2) -> o1.getNome().compareTo(o2.getNome());
    public static Comparator<Prodotto> SORT_BY_NAME_DESC = (o1, o2) -> o2.nome.compareTo(o1.nome);

    public static Comparator<Prodotto> SORT_BY_PREZZO_ASC = (o1, o2) -> o1.prezzoKg.compareTo(o2.prezzoKg);
    public static Comparator<Prodotto> SORT_BY_PREZZO_DESC = (o1, o2) -> o2.prezzoKg.compareTo(o1.prezzoKg);

    public static Comparator<Prodotto> SORT_BY_DATA_ASC = (o1, o2) -> o1.dataArrivo.compareTo(o2.dataArrivo);
    public static Comparator<Prodotto> SORT_BY_DATA_DESC = (o1, o2) -> o2.dataArrivo.compareTo(o1.dataArrivo);

    public static Comparator<Prodotto> SORT_BY_QUANTITA_ASC = (o1, o2) -> o1.quantita.compareTo(o2.quantita);
    public static Comparator<Prodotto> SORT_BY_QUANTITA_DESC = (o1, o2) -> o2.quantita.compareTo(o1.quantita);

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "prezzo_kg")
    private Double prezzoKg;

    @Column(name = "data_arrivo")
    private Date dataArrivo;

    @Column(name = "quantita")
    private Integer quantita;

    @ManyToMany
    @JoinTable(
            name = "acquisti",
            joinColumns = @JoinColumn(name = "prodotto_id"),
            inverseJoinColumns = @JoinColumn(name = "vendita_id")
    )
    private Collection<Vendita> vendite;

}
