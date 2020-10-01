from django.shortcuts import render

from rest_framework import viewsets

from .serializers import ReportSerializer
from .models import Report
from django.http import JsonResponse
from rest_framework.response import Response
from rest_framework import status
from rest_framework.decorators import api_view, renderer_classes

from analysis import Bands, Heart

class ReportViewSet(viewsets.ModelViewSet):
    queryset = Report.objects.all().order_by('BPM')
    serializer_class = ReportSerializer

@api_view(('GET',))
def get_report(request):

    dto = request.data

    if dto == None:
        return JsonResponse({'Error': 'No DTO provided'})
     
    ecg_analysis = Heart.Heart(dto.ecg)
    eeg_analysis = Bands.Bands(dto.eeg)

    bpm, breathrate = ecg_analysis.run_analysis()
    waves = eeg_analysis.get_bands()
    
    # TODO: Handle the Diff parameter and Spikes?
    m = Report(bpm, 0, breathrate, waves['Alpha'], waves['Beta'], waves['Delta'], waves['Theta'], waves['Gamma'])


    serializer = ReportSerializer(m, context={'request': request})
    return Response(serializer.data, status=status.HTTP_200_OK)