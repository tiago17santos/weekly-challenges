from django.shortcuts import render, redirect, get_object_or_404
from .models import Cadastro, Categorias

def cadastro(request):
     cadastro = Cadastro()

     if request.method == 'POST':

          # Recuperando os valores enviados pelo formulário
          cadastro.nome_prod = request.POST.get('nomeProd')
          cadastro.desc_prod = request.POST.get('descProd')
          cadastro.valor = request.POST.get('valorProd')
          cadastro.disp = True if request.POST.get('disp') == 'sim' else False  # Converter valores "sim"/"nao" para True/False
          cat_prod_id = request.POST.get('catProd')

          
           # Converter valores "sim"/"nao" para True/False
          if cadastro.disp == "sim":
               cadastro.disp = True
          elif cadastro.disp == "nao":
               cadastro.disp = False

          cadastro.valor = float(cadastro.valor)  # Certifica-se de que o valor seja numérico

          if cat_prod_id:  # Verifica se a categoria foi enviada
               categoria = get_object_or_404(Categorias, id=cat_prod_id)  # Busca a categoria
               cadastro.cat_prod = categoria
               cadastro.save()
               return redirect('listagem')  # Redireciona para a lista de produtos
          else:
               return render(request, 'cadastro.html', {'error': 'A categoria é obrigatória!'})


     categorias = Categorias.objects.all()
     context = {
          'categorias': categorias
     }
    
     return render(request, 'cadastro.html', context)


def listagem(request):
     produtos = Cadastro.objects.all() #Buscando todos os registros
     categorias = Categorias.objects.all()

     context = {
          'produtos': produtos,
          'categorias': categorias
     }
    
     return render(request, 'listagem.html', context)


def categoria(request):
     categoria = Categorias()

     if request.method == 'POST':
          categoria.nome = request.POST.get('nome')
          categoria.save()

     return render(request, 'categoria.html')

def editar_prod(request,pk):
     produto = get_object_or_404(Cadastro,id=pk) # Buscando o produto de acordo com o id
     categorias = Categorias.objects.all()

     context = {
          'produto': produto,
          'categorias': categorias,
          'cat_sel': produto.cat_prod_id if produto.cat_prod_id else None
     }

     if request.method == 'POST':
          produto.nome_prod = request.POST.get('nomeProd')
          produto.desc_prod = request.POST.get('descProd')
          produto.valor = request.POST.get('valorProd')
          produto.disp = True if request.POST.get('disp') == 'sim' else False  # Converter valores "sim"/"nao" para True/False
          cat_prod_id = request.POST.get('catProd')

          produto.valor = float(produto.valor)  # Certifica-se de que o valor seja numérico

          if cat_prod_id:  # Verifica se a categoria foi enviada
               categoria = get_object_or_404(Categorias, id=cat_prod_id)  # Busca a categoria
               produto.cat_prod = categoria
               produto.save()
               return redirect('listagem')  # Redireciona para a lista de produtos
          else:
               return render(request, 'editar_prod.html', {'error': 'A categoria é obrigatória!'})
    
     return render(request, 'editar_prod.html', context)


def excluir_item(request, pk):
     produto = get_object_or_404(Cadastro,id=pk)
     produto.delete() #deletando produto


     # Após excluir, renderiza novamente a página de listagem e recupera a lista de produtos atualizada
     produtos = Cadastro.objects.all()
     
     context = {
          'produtos': produtos,
     }

     return render(request, 'listagem.html', context)
