package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamentoDeConsultas {
    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorario = dataConsulta.getHour() < 7;
        var depoisDoHorario = dataConsulta.getHour() > 18;

        if (domingo || antesDoHorario || depoisDoHorario){
            throw new ValidacaoException("Horário escolhido para consulta está fora do horário de funcionamento da clínica.");
        }
    }
}
