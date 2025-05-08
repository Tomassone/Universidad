import random
import tkinter as tk
from tkinter import messagebox
import sys

def quiz():
    if len(sys.argv) > 1:
        file_name = sys.argv[1]
    else:
        file_name = 'domandeVerificate.txt'

    # Funzione per leggere e parseare le domande
    def leggi_domande(file_name):
        domande = []
        with open(file_name, 'r') as file:
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
    sbagliate = []
    current_question = 0

    def prossima_domanda(n):
        nonlocal current_question
        if n < len(domande):
            current_question = n + 1
            numero, domanda, risposta = domande[n]
            domanda_label.config(text=f"Domanda {current_question}: {domanda}")
            button_v.config(command=lambda: risposta_utente('V', risposta, n))
            button_f.config(command=lambda: risposta_utente('F', risposta, n))
        else:
            termina_quiz()

    def risposta_utente(risposta_utente, risposta_corretta, n):
        nonlocal punteggio
        if risposta_utente == risposta_corretta:
            punteggio += 1
        else:
            sbagliate.append(domande[n])
        prossima_domanda(n + 1)

    def termina_quiz():
        messagebox.showinfo("Punteggio", f"Hai ottenuto un punteggio di {punteggio} su 40.")
        if sbagliate:
            # Apri una nuova finestra per mostrare le domande sbagliate
            finestra_sbagliate = tk.Toplevel(root)
            finestra_sbagliate.title("Domande Sbagliate")

            label_sbagliate = tk.Label(finestra_sbagliate, text="Domande Sbagliate:")
            label_sbagliate.pack()

            for i, domanda in enumerate(sbagliate):
                label_domanda = tk.Label(finestra_sbagliate, text=f"{i+1}. {domanda[1]} (Risposta corretta: {'Vero' if domanda[2] == 'V' else 'Falso'})")
                label_domanda.pack()

            # Distruggi la finestra principale quando la finestra delle domande sbagliate viene chiusa
            finestra_sbagliate.protocol("WM_DELETE_WINDOW", root.destroy)
        else:
            root.destroy()

    # Crea la finestra principale
    root = tk.Tk()
    root.title("Quiz")

    # Imposta una dimensione minima e massima
    root.minsize(800, 400)
    root.geometry("800x400")

    domanda_label = tk.Label(root, text="", wraplength=750, justify="left", font=("Arial", 13, "bold"))
    domanda_label.pack(pady=30)

    button_v = tk.Button(root, text="Vero", width=30, height=5, command=lambda: risposta_utente('V', '', 0), bg="green", fg="black", font=("Arial", 15, "bold"))
    button_v.pack(side=tk.LEFT, padx=40, pady=20)

    button_f = tk.Button(root, text="Falso", width=30, height=5, command=lambda: risposta_utente('F', '', 0), bg="red", fg="black", font=("Arial", 15, "bold"))
    button_f.pack(side=tk.RIGHT, padx=40, pady=20)

    button_termina = tk.Button(root, text="Termina", width=30, height=2, command=termina_quiz, bg="blue", fg="black", font=("Arial", 15, "bold"))
    button_termina.pack(side=tk.BOTTOM, padx=40, pady=20)

    prossima_domanda(0)

    root.mainloop()

if __name__ == "__main__":
    quiz()
