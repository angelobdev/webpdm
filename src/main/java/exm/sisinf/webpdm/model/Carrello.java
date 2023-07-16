package exm.sisinf.webpdm.model;

import exm.sisinf.webpdm.model.support.CarrelloProdotto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Entity
@Table(name = "carrelli")
@Data
@NoArgsConstructor
public class Carrello {

    @Id
    @GeneratedValue(generator = "carrelli_id_seq")
    @SequenceGenerator(name = "carrelli_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "acquistato")
    private Boolean acquistato;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarrelloProdotto> carrelloProdotti = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CARRELLO #" + id);
        sb.append("\nAcquistato: " + (acquistato ? "Si" : "No"));
        sb.append("\nUtente: " + (utente.getUsername()));

        sb.append("Prodotti e Quantità:");

        for (var pq : carrelloProdotti) {
            sb.append(pq.getProdotto() + ", " + pq.getQuantita() + "\n");
        }

        return sb.toString();
    }

}
