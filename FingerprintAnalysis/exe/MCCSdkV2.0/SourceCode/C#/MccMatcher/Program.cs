// Copyright (C) 2015 DISI - University of Bologna (Italy)
// All rights reserved.
//
// MCC SDK sample source code.
// http://biolab.csr.unibo.it/mccsdk.html
//
// This source code can be used to create MCC SDK executables.
// It cannot be distributed and any other use is strictly prohibited.
//
// Warranties and Disclaimers:
// THIS SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND
// INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT.
// IN NO EVENT WILL UNIVERSITY OF BOLOGNA BE LIABLE FOR ANY DIRECT,
// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY OR CONSEQUENTIAL DAMAGES,
// INCLUDING DAMAGES FOR LOSS OF PROFITS, LOSS OR INACCURACY OF DATA,
// INCURRED BY ANY PERSON FROM SUCH PERSON'S USAGE OF THIS SOFTWARE
// EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
//

// ---------------------------------------------------------------
//          MCC SDK MccMatcher executable
//          v 2.0 - July 2015
// ---------------------------------------------------------------

using System;
using System.IO;
using System.Globalization;
using BioLab.Biometrics.Mcc.Sdk;

namespace MccMatcher
{
  class Program
  {
    static void Main(string[] args)
    {
      if (args.Length != 4 &&
          args.Length != 5)
      {
        Console.WriteLine("\nSyntax error.\nUse: MccMatcher <MccTemplateFile1> <MccTemplateFile2> <MccMatchParametersFile> <OutputFile> [MccEnrollParametersFile]\n");
        Console.WriteLine("[MccEnrollParametersFile] is only needed to load MccTemplateFiles stored in text format.\n");
        return;
      }
      var mccTemplateFile1 = args[0];
      var mccTemplateFile2 = args[1];
      var mccMatchParamFile = args[2];
      var outputFile = args[3];
      var mccEnrollParamFile = args.Length == 5 ? args[4] : null;

      var matchPerformed = true;
      var score = -1.0;
      try
      {
        if (mccEnrollParamFile != null)
        {
          MccSdk.SetMccEnrollParameters(mccEnrollParamFile);
        }

        MccSdk.SetMccMatchParameters(mccMatchParamFile);

                /*var template1 = LoadCylinderTemplateFromFile(mccTemplateFile1);
                var template2 = LoadCylinderTemplateFromFile(mccTemplateFile2);*/

                var template1 = MccSdk.CreateMccTemplateFromTextTemplate(mccTemplateFile1);
                var template2 = MccSdk.CreateMccTemplateFromTextTemplate(mccTemplateFile2);
                //MccSdk.SaveMccTemplateToTextFile(template1, "D:\\Users\\Malik\\Desktop\\STAGE GREYC\\SDKs\\results\\t1.txt");
                //MccSdk.SaveMccTemplateToTextFile(template2, "D:\\Users\\Malik\\Desktop\\STAGE GREYC\\SDKs\\results\\t2.txt");

                score = MccSdk.MatchMccTemplates(template1, template2);
      }
      catch (Exception e)
      {
        matchPerformed = false;
        Console.WriteLine(e);
      }

      File.AppendAllText(outputFile, string.Format(CultureInfo.InvariantCulture,
                                                    "{0} {1} {2} {3:R}\r\n",
                                                    mccTemplateFile1,
                                                    mccTemplateFile2,
                                                    matchPerformed ? "OK" : "FAIL",
                                                    score));
    }

    private static object LoadCylinderTemplateFromFile(string filePath)
    {
      object template = null;
      switch (Path.GetExtension(filePath).ToLowerInvariant())
      {
        case ".mcc":
          template = MccSdk.LoadMccTemplateFromBinaryFile(filePath);
          break;
        case ".txt":
          template = MccSdk.LoadMccTemplateFromTextFile(filePath);
          break;
        default:
          throw new Exception("Template file format not supported");
      }

      return template;
    }
  }
}
