package br.com.rapidmotorbike.rapidmotorbike.models;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payments_infos")
public class PaymentsInfos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;

    @Column(name = "card_number", nullable = true)
    private int card_number;

    @Column(name = "card_security_code", nullable = true)
    private int card_security_code;

    @Column(name = "card_due_date", nullable = true)
    private LocalDate card_due_date;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "payment_type", nullable = false)
    private int payment_type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public int getCard_security_code() {
        return card_security_code;
    }

    public void setCard_security_code(int card_security_code) {
        this.card_security_code = card_security_code;
    }

    public LocalDate getCard_due_date() {
        return card_due_date;
    }

    public void setCard_due_date(LocalDate card_due_date) {
        this.card_due_date = card_due_date;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }

}
