import re


def Validacao():

    cpf = str(input('Digite o CPF: '))

    numeros = [int(digito) for digito in cpf if cpf.isdigit()]


    formatacao = False
    qtd_numeros = False
    validacao1 = False
    validacao2 = False


    #Verifica a estrutura do CPF (111.222.333-44)
    if re.match(r'\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\', cpf):
        formatacao = True
    
    if len(numeros) == 11:
        qtd_numeros = True

        soma_produtos1 = sum(a * b for a,b in zip(numeros[0:9], range(10,1,-1)))
        digito_esperado1 = (soma_produtos1 * 10 % 11) % 10
        if digito_esperado1 == numeros[9]:
            validacao1 = True
        
        soma_produtos2 = sum(a * b for a,b in zip(numeros[0:10], range(11,1,-1)))
        digito_esperado2 = (soma_produtos2 * 10 % 11) % 10
        if(digito_esperado2 == numeros[10]):
            validacao2 = True

        if qtd_numeros & formatacao & validacao1 & validacao2:
            print(f'O CPF: {cpf} é valido')
        else:
            print("Digite um CPF válido.")
        

    else:
        print(f'O CPF: {cpf} não é valido')



Validacao()