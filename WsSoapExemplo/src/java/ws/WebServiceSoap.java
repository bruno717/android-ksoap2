package ws;

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import model.Aluno;

/**
 *
 * @author Bruno
 */
@WebService(serviceName = "WebServiceSoap")
public class WebServiceSoap {

    /**
     * Operação de Web service
     *
     * @param texto
     * @return
     */
    @WebMethod(operationName = "getString")
    public String getString(@WebParam(name = "texto") String texto) {
        return "Retorno do web service: " + texto;
    }

    /**
     * Operação de Web service
     *
     * @param aluno
     * @return
     */
    @WebMethod(operationName = "getAluno")
    public Aluno getAluno(@WebParam(name = "aluno") Aluno aluno) {
        aluno.setId(15);
        return aluno;
    }

    /**
     * Operação de Web service
     *
     * @param lista
     * @return
     */
    @WebMethod(operationName = "getListAlunos")
    public List<Aluno> getListAlunos(@WebParam(name = "lista") List<Aluno> lista) {
        List<Aluno> alunos = new ArrayList<Aluno>();
        alunos.add(lista.get(lista.size() - 1));
        alunos.add(new Aluno(2, "Carlos Araújo", "Analise e desenvolvimento de sistemas"));
        alunos.add(new Aluno(3, "Marcela Regina ", "Administração"));
        alunos.add(new Aluno(4, "José Antunes ", "Engenharia química"));
        return alunos;
    }

    /**
     * Operação de Web service
     *
     * @param lista
     * @return
     */
    @WebMethod(operationName = "getArrayString")
    public String[] getArrayString(@WebParam(name = "lista") String[] lista) {
        return lista;
    }
}
