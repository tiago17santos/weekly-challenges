from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver import ActionChains
import time 


class CookieClicker():
    def __init__(self):
        self.site_link = 'https://orteil.dashnet.org/cookieclicker/'
        self.site_map = {
            "buttons": {
                "cookie":{
                    "xpath": '/html/body/div/div[2]/div[15]/div[8]/button'
                },
                "upgrades":{
                    "xpath":'/html/body/div/div[2]/div[19]/div[3]/div[6]/div[$$NUMBER$$]'
                },
                "language":{
                    "xpath": '/html/body/div/div[2]/div[12]/div/div[1]/div[1]/div[10]'
                }
            }
        }

        self.servico = Service(executable_path = "C:\\Users\\tiago\\anaconda3\\chromedriver.exe")
        self.driver = webdriver.Chrome(service=self.servico)
        self.driver.maximize_window()

    def abrir_site(self):
        time.sleep(2)
        self.driver.get(self.site_link)
        time.sleep(5)

    def seleciona_idioma(self):
        click_mouse = self.driver.find_element(By.XPATH, self.site_map['buttons']['language']['xpath'])
        ActionChains(self.driver).click(click_mouse).perform()
        time.sleep(5)
    

    def clicar_cookie(self):
        click_mouse = self.driver.find_element(By.XPATH, self.site_map['buttons']['cookie']['xpath'])

        ActionChains(self.driver).click(click_mouse).perform()

    def pegar_melhor_upgrade(self):
        encontrei = False
        elemento_atual = 2

        while not encontrei:
            objeto = self.site_map['buttons']['upgrades']['xpath'].replace('$$NUMBER$$', str(elemento_atual))
            classes_objeto = self.driver.find_element(By.XPATH, objeto).get_attribute('class')
            
            if not 'enable' in classes_objeto:
                encontrei = True
            else:
                elemento_atual += 1
        return elemento_atual - 1

    def comprar_upgrade(self):
        print(self.pegar_melhor_upgrade())
        
        objeto = self.site_map['buttons']['upgrades']['xpath'].replace('$$NUMBER$$', str(self.pegar_melhor_upgrade()))
        click_mouse = self.driver.find_element(By.XPATH, objeto)
        ActionChains(self.driver).click(click_mouse).perform()


biscoito = CookieClicker()
biscoito.abrir_site()
biscoito.seleciona_idioma()

i = 0

while True:
    if i % 250 == 0 and i != 0:
        time.sleep(5)
        biscoito.comprar_upgrade()
        time.sleep(5)
    biscoito.clicar_cookie()

    i += 1    