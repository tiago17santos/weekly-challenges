import openai

#openai.api_key = "SUA API"

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


def main():

    print("\nBem vindo ao GPT-4 Chatbot do projeto 3 do curso ")
    print("(Digite 'sair' a qualquer momento para encerrar o chat)")

    while True:
        user_message = input("\nVocê: ")

        if user_message.lower() == "sair":
            break

        gpt4_prompt = f"\nUsuário: {user_message}\nChatbot:"

        chatbot_response = gera_texto(gpt4_prompt)

        print(f"\nChatbot: {chatbot_response}")


if __name__ == "__main__":
    main()