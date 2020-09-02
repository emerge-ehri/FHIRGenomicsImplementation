# FHIR Genomics Implementation

This is a proof-of-concept application developed to demonstrate the creation of FHIR reports adhering to [eMERGE's specification for FHIR Genomics Reporting Implementation](https://emerge-fhir-spec.readthedocs.io/en/latest/). FHIR report bundles developed with this implementation are available at [Sample Reports](https://github.com/emerge-ehri/fhir-specification/tree/master/Sample%20Reports).


This program demonstrates the ability to read proprietary BCM-HGSC genetic results from AWS S3, create FHIR resources and profiles using [HAPI FHIR API](https://hapifhir.io/) from the source data and push the same to a [Smile CDR FHIR server](https://smilecdr.com/smilecdr.html) for validation and storage. The FHIR bundles are also stored in AWS S3.

## Setup



## Running

