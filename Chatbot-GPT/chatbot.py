import openai

openai.api_key = "sk-proj-sJ4leTWkD0Pkxyggg9ABT3BlbkFJbcL2WrBnvxMgHiSBVR1P"

def gera_texto(texto):

    
    response = openai.Completion.create(

        engine = "gpt-3.5-turbo",
        prompt = texto,
        max_tokens = 1024,
        n = 1,
        stop = None,
        temperature = 0.5
        
    )

    return response.choices[0].text.strip()


