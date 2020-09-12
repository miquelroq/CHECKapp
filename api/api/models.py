from django.db import models


class Report(models.Model):
    """
    Report Class
    This is the class that will be serialized whenever a GET to the main endpoint is made

    It encapsulates the relevant data for each of the BaseValuesHandler parameters
    It is worth discussing if we want to keep fields as Integers or Doubles
    """
    
    BPM = models.IntegerField()
    FREQ = models.IntegerField()
    BREATH = models.IntegerField()
    BMI = models.IntegerField()
    FAT = models.IntegerField()
    MDF = models.IntegerField()
    MNF = models.IntegerField()
    RMS = models.IntegerField()
    RRMS = models.IntegerField()
    ALPHA = models.IntegerField()
    BETA = models.IntegerField()
    DELTA = models.IntegerField()
    THETA = models.IntegerField()

    def __str__(self):
        return "Cool Report"
