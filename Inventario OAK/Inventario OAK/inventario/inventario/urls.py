from django.contrib import admin
from django.urls import path
from app_inventario import views


urlpatterns = [
    path('', views.cadastro, name='cadastro'),
    path('listagem', views.listagem, name='listagem'),
    path('categoria', views.categoria, name='categoria'),
    path('editar_prod/<int:pk>', views.editar_prod, name='editar_prod'),
    path('excluir_item/<int:pk>', views.excluir_item, name='excluir_item'),
    
]
