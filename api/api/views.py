from django.shortcuts import render

from rest_framework import viewsets

from .serializers import ReportSerializer
from .models import Report
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view, renderer_classes

class ReportViewSet(viewsets.ModelViewSet):
    queryset = Report.objects.all().order_by('BPM')
    serializer_class = ReportSerializer

@api_view(('GET',))
def get_report(request, dto=None):
     m = Report()

     # TODO: Generate the report with the dto passed along as parameter

     serializer = ReportSerializer(m, context={'request': request})
     return Response(serializer.data, status=status.HTTP_200_OK)