import random
import time
import sys

while True:
    # Genera un valore casuale tra 47 e 
    time.sleep(2)
    valore = random.randint(47, 54)
    print(valore)
    sys.stdout.flush()
    
    # Genera un intervallo di tempo casuale tra 0 e 7 secondi
    attesa = random.uniform(0, 50)
    time.sleep(attesa)