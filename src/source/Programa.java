package source;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Programa {

    public static void main(String[] args) {
        Leitor leitor = null;
        
        //le expressoes e monta cada expressao em uma arvore
        try {
            leitor = new Leitor("expressoes.txt");
            String linhaAtual;
            //itera sobre cada uma das expressoes (linhas do arquivo)
            do {
                linhaAtual = leitor.leProximaLinha();
                if(linhaAtual == null)
                    continue;
                
                System.out.println("Expressao: " + linhaAtual);
                int quantidadeAbreParenteses = linhaAtual.split("\\(", -1).length;
                int quantidadeFechaParenteses = linhaAtual.split("\\)", -1).length;
                if(quantidadeAbreParenteses != quantidadeFechaParenteses) {
                    System.out.println("Expressao com erro de erro de sintaxe");
                    continue;
                }
                
                BinaryTreeOfString arvore = new BinaryTreeOfString();
                arvore.addRoot("");
                
                String[] termos = linhaAtual.split(" ");
                //ignora o primeiro e o ultimo parenteses
                for(int i = 1; i < termos.length - 1; i++) {
                    switch (termos[i]) {
                        case "(":
                            arvore.addAndMoveCursorToLowerLevel("");
                            break;
                        case ")":
                            arvore.returnCursorToUpperLevel();
                            break;
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                        case "^":
                            arvore.setValueOnCursor(termos[i]);
                            break;
                        default:
                            arvore.addOperando(termos[i]);
                            break;
                    }
                }
                
                if(!arvore.verificaSeEstaNaRaiz())
                    throw new Exception("Erro ao percorrer a arvore!");
                
                System.out.println("Caminhamento central: " + arvore.positionsCentral().toString());
                System.out.println("Caminhamento em largura: " + arvore.positionsWidth().toString());
                //System.out.println("Caminhamento pós: " + arvore.positionsPos().toString());
                //System.out.println("Caminhamento pré: " + arvore.positionsPre().toString());
                System.out.println("Altura: " + arvore.height());
                System.out.println("Resultado: " + arvore.calcular());
            } while(linhaAtual != null);
        } 
        catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado.");
        }
        catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        } catch (Exception ex) {
            System.out.println("Erro:" + ex.getMessage());
        }
        finally {
            try {
                leitor.fechaArquivo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}