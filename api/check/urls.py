from django.urls import path
from . import views

urlpatterns = [
    path(r'', views.check, name='check'),
]