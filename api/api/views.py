from django.shortcuts import render

from rest_framework import viewsets

from .serializers import ReportSerializer
from .models import Report


class ReportViewSet(viewsets.ModelViewSet):
    queryset = Report.objects.all().order_by('BPM')
    serializer_class = ReportSerializer