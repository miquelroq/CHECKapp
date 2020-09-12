from rest_framework import serializers

from .models import Report

class ReportSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Report
        fields = (
                    'BPM', 
                    'FREQ',
                    'BREATH',
                    'BMI',
                    'FAT',
                    'MDF',
                    'MNF',
                    'RMS',
                    'RRMS',
                    'ALPHA',
                    'BETA',
                    'DELTA',
                    'THETA'
                )