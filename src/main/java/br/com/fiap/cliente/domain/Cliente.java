package br.com.fiap.cliente.domain;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Getter
@Setter
public class Cliente {
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private LocalDate dataCadastro;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    private static final String TELEFONE_REGEX = "^(\\+\\d{1,3}\\s?)?(\\(?\\d{2}\\)?\\s?)?(9\\d{4}|\\d{4})[-.\\s]?\\d{4}$";
    private static final Pattern telefonePattern = Pattern.compile(TELEFONE_REGEX);


    public Cliente(String nome, String email, String telefone, String endereco, LocalDate dataCadastro) {
        isValidoEmail(email);
        isValidoTelefone(endereco);

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public void isValidoEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Não foi informado o email");
        }
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    public void isValidoTelefone(String telefone) {
        if (telefone == null) {
            throw new IllegalArgumentException("Não foi informado o telefone");
        }
        Matcher matcher = telefonePattern.matcher(telefone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Telefone inválido");
        }
    }

}
