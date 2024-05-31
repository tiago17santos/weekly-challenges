from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader
import requests

# Create your views here.
def previsao(request):
    template = loader.get_template('app.html')
   
    cidade = request.GET.get('search')

    chave_api = 'ab1db5d5894858d8593b78c175f66e2a'

    url = f"http://api.openweathermap.org/data/2.5/forecast?q={cidade}&units=metric&appid={chave_api}&lang=pt_br"


    response = requests.get(url).json()


    return render(request, 'app.html', response)