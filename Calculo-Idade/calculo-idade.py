
diaNasc = input("Qual o dia do seu nascimento?")
mesNasc = input("Qual o mês do seu nascimento?")
anoNasc = input("Qual o ano do seu nascimento?")

diaAtual = input("Qual o dia atual?")
mesAtual = input("Qual o mês atual?")
anoAtual = input("Qual o ano atual?")


def CalculaIdade(diaNasc,mesNasc,anoNasc,diaAtual,mesAtual,anoAtual):
    
    difAno = anoAtual - anoNasc
    
    if mesAtual < mesNasc | (mesAtual == mesNasc & diaAtual < diaNasc):
        difAno -= 1

    difMes = mesAtual - mesNasc

    if difMes < 0:
        difMes += 12


    difDia = diaAtual - diaNasc

    if diaAtual < 0:
        difDia += 30

        if difMes < 0:
            difMes += 12
            difAno -= 1


    return f"Você tem {difAno} anos,{difMes} meses e {difDia} dias"

idade = CalculaIdade(diaNasc,mesNasc,anoNasc,diaAtual,mesAtual,anoAtual)
print(idade)