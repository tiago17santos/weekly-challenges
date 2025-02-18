# Generated by Django 5.1.4 on 2025-01-14 21:00

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Categorias',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome', models.CharField(max_length=255)),
            ],
        ),
        migrations.CreateModel(
            name='Cadastro',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('nome_prod', models.CharField(max_length=255)),
                ('desc_prod', models.TextField()),
                ('valor', models.DecimalField(decimal_places=2, max_digits=12)),
                ('disp', models.BooleanField(default=True)),
                ('cat_prod', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='app_inventario.categorias')),
            ],
        ),
    ]
