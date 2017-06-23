package kmeans;

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Math.abs;

public class Kmeans {

    public static void main(String args[]) {

        ArrayList<Coordenadas> texto = new ArrayList<>();

        String centroides[];
        String centroideAnterior[];
        String grupos[][];
        String nums[];
        String palavra[];
        
        int numeroGrupo = 0;

        double distancias[][];
        double min, count, count2, k, iteracoes = 1, soma, soma2, ok = 1, sum = 0, sumAnterior = 0, erro = 0;

        String value = null;
        centroides = new String[25];
        centroideAnterior = new String[25];
        distancias = new double[25][100];
        grupos = new String[25][100];
        
        nums = new String[100];

        Linhas p = new Linhas();
        texto = p.Linha();

        for (int i = 0; i < texto.size(); i++) {
            nums[i] = texto.get(i).retorno();
        }

        System.out.println("BASE DE DADOS: ");
        for (int i = 0; i < texto.size(); i++) {
            System.out.print("(" + nums[i] + ") ");
        }

        System.out.println("\n");

        Scanner entrada = new Scanner(System.in);
        System.out.println("Entre com o valor de K");
        k = entrada.nextInt();

        System.out.println("Numero k = " + k);
        System.out.println("\n");

        //Centroides iniciais
        for (int i = 0; i < k; i++) {
            
            int cent = (int) (Math.random() * 400);
            int cent2 = (int) (Math.random() * 400);
            
            centroides[i] = cent+", "+cent2;
            
            //int cent = (int) (Math.random() * text.size());
            //centroides[i] = nums[cent];
        }

        while (ok == 1) {
            
            sumAnterior = sum;
            sum = 0;
            
            System.out.println("NUMERO DE ITERAÇÕES: " + iteracoes);

            for (int i = 0; i < k; i++) {
                //Para cada centroide calcula-se a distancia para cada ponto
                for (int j = 0; j < texto.size(); j++) {

                    palavra = centroides[i].split(", ");
                    double cent = Double.parseDouble(palavra[0]);
                    double cent2 = Double.parseDouble(palavra[1]);

                    palavra = nums[j].split(", ");
                    double val = Double.parseDouble(palavra[0]);
                    double val2 = Double.parseDouble(palavra[1]);

                    //Euclidiana
                    distancias[i][j] = Math.sqrt(Math.pow((cent - val), 2) + Math.pow((cent2 - val2), 2));

                }
            }

            //Construir Grupos
            for (int i = 0; i < texto.size(); i++) {
                min = 100000;

                for (int j = 0; j < k; j++) {
                    //Escolher as menores distancias
                    if (distancias[j][i] < min) {
                        min = distancias[j][i];
                        
                        value = nums[i];
                        numeroGrupo = j;
                    }
                    /*
                    No final desse passo teremos
                    -A Distancia minima de cada coordenada da Matriz até o centroide
                    -O Valor da base correspondente a essa distancia
                    -O Grupo a qual vai pertencer o elemento
                     */
                }
                grupos[numeroGrupo][i] = value;
                //Criação da Matriz de grupos
            }

            //Mostar os resultado dos calculos das distancias
            System.out.println("CÁLCULO DAS DISTANCIAS ");

            for (int i = 0; i < texto.size(); i++) {
                for (int j = 0; j < k; j++) {
                    System.out.println(distancias[j][i]);
                }
                System.out.println("\n");
            }

            //Mostrar grupos e centroides
            System.out.println("\nGRUPOS");
            for (int i = 0; i < k; i++) {
                soma = 0;
                soma2 = 0;
                count = 0;
                count2 = 0;

                System.out.println("GRUPO " + (i + 1) + ": ");

                for (int j = 0; j < texto.size(); j++) {

                    if (grupos[i][j] != null) {
                        
                        //Todos elementos do grupo
                        System.out.print("(" + grupos[i][j] + ") ");

                        palavra = grupos[i][j].split(", ");
                        double val = Double.parseDouble(palavra[0]);
                        double val2 = Double.parseDouble(palavra[1]);

                        soma += val;
                        soma2 += val2;

                        count++;
                        count2++;
                        /*
                        Mostra a matriz de Grupos e Pega a somatoria dos elementos e a quantidade
                        para recalcular o centroide*/
                    }
                }
                centroideAnterior[i] = centroides[i];

                double aux = soma / count;
                double aux2 = soma2 / count2;

                centroides[i] = aux + ", " + aux2;
                
                sum += aux + aux2;

                //O centroide é recalculado mas o centroide anterior é guardado para condição de parada
                System.out.println("\nCENTROIDE: (" + centroides[i] + ")\n");
            }

            //Inicializar matriz de grupos com NULL
            for (int i = 0; i < 25; i++) {
                for (int j = 0; j < 100; j++) {
                    grupos[i][j] = null;
                }
            }

            //Verificar condições de término
            ok = 0;
            for (int i = 0; i < k; i++) {
                if (!centroideAnterior[i].equals(centroides[i])) {
                    ok = 1;
                }
            }
            /*Se não houver alterações na Media, o programa finaliza guardando o numero de iterações
            realizadas*/
            iteracoes++;

            
            erro = sumAnterior - sum;
            
            if(ok==1){
                   System.out.println("ERRO: " + abs(erro));    
            }
            System.out.println("\n");
        }
    }
}
