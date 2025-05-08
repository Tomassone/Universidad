import random
import sys

def quiz():
    if len(sys.argv) > 1:
        file_name = sys.argv[1]
    else:
        file_name = 'domandeVerificate.txt'

    # Funzione per leggere e parseare le domande
    def leggi_domande(file_name):
        domande = []
        with open(file_name, 'r', encoding='utf-8') as file:
            for line in file:
                parts = line.strip().split(' – ')
                if len(parts) == 2:
                    domanda, risposta = parts
                    domanda_split = domanda.split('. ', 1)
                    if len(domanda_split) == 2:
                        numero_domanda = domanda_split[0]
                        testo_domanda = domanda_split[1]
                        domande.append((numero_domanda, testo_domanda, risposta))
        return domande

    # Leggi le domande dal file
    domande = leggi_domande(file_name)
    random.shuffle(domande)
    domande = domande[:40]  # Prendi solo le prime 40 domande

    punteggio = 0

    # Itera attraverso le domande
    for numero_domanda, testo_domanda, risposta_corretta in domande:
        print(f"Domanda {numero_domanda}: {testo_domanda}")
        risposta_utente = input("La tua risposta: ")
        if risposta_utente.lower() == risposta_corretta.lower():
            punteggio += 1
            print("Corretto!")
        else:
            print(f"Sbagliato! La risposta corretta era: {risposta_corretta}")

    print(f"Hai ottenuto un punteggio di {punteggio} su 40")

if __name__ == "__main__":
    quiz()