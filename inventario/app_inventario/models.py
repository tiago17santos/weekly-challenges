from django.db import models

class Categorias(models.Model):
    nome = models.CharField(max_length=255)

    
class Cadastro(models.Model):
    nome_prod = models.CharField(max_length=255)
    desc_prod = models.TextField()
    valor = models.DecimalField(max_digits=12, decimal_places=2)
    disp = models.BooleanField(default=True)
    cat_prod =  models.ForeignKey(Categorias, on_delete=models.CASCADE)


