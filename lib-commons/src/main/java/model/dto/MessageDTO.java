package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descricao;
    private String status;
    private String topico;

    public MessageDTO(String descricao, String status, String topico) {
        //Gera id aleatório com 5 posições.
        Random random = new Random();
        int idAleatorio = random.nextInt(90000) + 10000;
        this.id = idAleatorio;
        this.descricao = descricao;
        this.status = status;
        this.topico = topico;
    }

}
