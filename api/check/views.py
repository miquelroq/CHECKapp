# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.shortcuts import render

def check(request):
    return render(request, 'check.html', {})

# Create your views here.
