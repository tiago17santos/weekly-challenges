import requests


chave_api = 'ab1db5d5894858d8593b78c175f66e2a'

cidade = 'São Paulo'

url = f"http://api.openweathermap.org/data/2.5/forecast?q={cidade}&units=metric&appid={chave_api}&lang=pt_br"


response = requests.get(url).json()

print(response['city']['name'] )
print(response['list'][0]['main']['temp'])
print(response['list'][0]['weather'][0]['description'])
print(response['list'][0]['main']['humidity'])
print(response['list'][0]['wind']['speed'])


