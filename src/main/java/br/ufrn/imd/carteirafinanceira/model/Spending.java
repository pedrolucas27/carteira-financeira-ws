package br.ufrn.imd.carteirafinanceira.model;

enum STATUS{
    PAGO,
    PENDENTE
}
public class Spending extends Transaction{
  //  STATUS status_pagamento;

    int status_pagamento;
    int id_categoria;


    public int getStatus_pagamento() {
        return status_pagamento;
    }

    public Spending(int status_pagamento, int id_categoria, int id_transaco) {
        this.status_pagamento = status_pagamento;
        this.id_categoria = id_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }


}
