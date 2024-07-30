import PySimpleGUI as sg

sg.theme('Reddit')
'''
layout = [
    [sg.Text('usuario'), sg.InputText(key='usuario')],
    [sg.Text('senha'), sg.InputText(key='senha')],
    [sg.Button('Calcular')],
    [sg.Text('',key='idade')],
] 

janela = sg.Window('Tela de login',layout)

while True:
    eventos, valores = janela.read()

    if eventos == sg.WINDOW_CLOSED:
       break
    if eventos == 'Calcular':
        janela['idade'].update('15 anos')

        print('Foi') 

janela.close()

'''

coluna1 = [
    [sg.Text("Dia Nascimento", font="arial 12")],
    [sg.Text("Mês Nascimento", font="arial 12")],
    [sg.Text("Ano Nascimento", font="arial 12")],
    [sg.Text("Dia Atual", font="arial 12")],
    [sg.Text("Mês Atual", font="arial 12")],
    [sg.Text("Ano Atual", font="arial 12")],
    [sg.Button('Calcular')],
    

]

coluna2 = [

    [sg.Input(font="arial 12", key="DiaNasc", size=(30, 20))],
    [sg.Input(font="arial 12", key="MesNasc", size=(30, 20))],
    [sg.Input(font="arial 12", key="AnoNasc", size=(30, 20))],
    [sg.Input(font="arial 12", key="DiaAtual", size=(30, 20))],
    [sg.Input(font="arial 12", key="MesAtual", size=(30, 20))],
    [sg.Input(font="arial 12", key="AnoAtual", size=(30, 20))],
    [sg.Text('',key='idade')],
]

layout = [
    [sg.Text("Calculadora de Idade", font="arial 20 bold")],
    [sg.Column(coluna1), sg.Column(coluna2)],

]

janela = sg.Window(
    "Análise",
    element_justification="left",
    element_padding=(0, 10),
    layout=layout,
    size=(500, 400),
    finalize=True,
)

while True:

    eventos, valores = janela.read()

    diaNasc = int(valores['DiaNasc'])
    mesNasc = int(valores['MesNasc'])
    anoNasc = int(valores['AnoNasc'])

    diaAtual = int(valores['DiaAtual'])
    mesAtual = int(valores['MesAtual'])
    anoAtual = int(valores['AnoAtual'])


    #função para calcular idade
    def CalculaIdade(diaNasc,mesNasc,anoNasc,diaAtual,mesAtual,anoAtual):
        
        difAno = anoAtual - anoNasc
        
        if mesAtual < mesNasc | (mesAtual == mesNasc & diaAtual < diaNasc):
            difAno -= 1

        difMes = mesAtual - mesNasc

        if difMes < 0:
            difMes += 12


        difDia = diaAtual - diaNasc

        if difDia < 0:
            difDia += 30

            if difMes < 0:
                difMes += 12
                difAno -= 1


        return f"Você tem {difAno} anos, {difMes} meses e {difDia} dias"

    idade = CalculaIdade(diaNasc,mesNasc,anoNasc,diaAtual,mesAtual,anoAtual)



    if eventos == sg.WINDOW_CLOSED:
       break
    if eventos == 'Calcular':
        janela['idade'].update(idade)
        


janela.close()
