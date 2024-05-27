import requests




url = "http://apiadvisor.climatempo.com.br/api/v1/anl/synoptic/locale/BR?token=286d19584ffa29dff7425640b92463eb"



response = requests.get(url)

print(response.json())