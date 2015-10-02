Sub Releasemacro()
    Dim Rc, ro
    Application.ScreenUpdating = False
    Application.ActiveWorkbook.Sheets("Daily Data").Select
    Range("d1").Select
    Selection.End(xlDown).Select
    ro = ActiveCell.Row
    Range("A2:W" & ro).Select
    Selection.ClearContents
    Range("A2").Select
    'response = MsgBox("Content Deleted.Do you wish to continue?", vbYesNo, "Continue")
     'If response = vbYes Then
     
    'pasting the data from base sheet
    
    Sheets("Base").Select
    Range("D1").Select
    Selection.End(xlDown).Select
    ro = ActiveCell.Row()
    ActiveSheet.Range("a2:v" & ro).Select
    'Application.CutCopyMode = False
    Selection.Copy
    Sheets("Daily Data").Select
    Range("a2").Select
   
    Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
    Range("n2:v" & ro).Select
    Selection.Cut
    ActiveCell.Offset(0, 1).Select
    ActiveSheet.Paste
    
    
    'Range("o2:v" & ro).Select
    'Selection.PasteSpecial Paste:=xlPasteValues, Operation:=xlNone, SkipBlanks _
        :=False, Transpose:=False
        
    Application.CutCopyMode = False
    'ActiveSheet.Paste
    
    
    'Module classification
    'sub module
    
    Range("o1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*non*" _
        , Operator:=xlAnd
    Range("v1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*non*" _
        , Operator:=xlAnd
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    'a.Select
    ActiveCell.FormulaR1C1 = "Non OP/QA"
    'Selection.Copy
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
    Selection.AutoFilter
    Application.CutCopyMode = False
    
    Range("v1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*qa*" _
        , Operator:=xlAnd
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "QA"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
        
    
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*op*" _
        , Operator:=xlAnd
        
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "OP"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
    
   
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*mainframe*" _
        , Operator:=xlAnd
        
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "Mainframe"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
    
    
     ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*t3*" _
        , Operator:=xlAnd
        ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "T3"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
   
        
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=22, Criteria1:="=*isd*" _
        , Operator:=xlAnd
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "ISD"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
    Application.CutCopyMode = False
    
    'description
    
    Range("v1").Select
    Selection.AutoFilter
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=13, Criteria1:="=*t3*" _
        , Operator:=xlAnd
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "T3"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
    
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=13, Criteria1:="=*op*" _
        , Operator:=xlAnd
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "OP"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
   
    
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=13, Criteria1:="=*qa*" _
        , Operator:=xlAnd
    ActiveSheet.Range("$A$1:$AD$" & ro).AutoFilter Field:=14, Criteria1:="="
    Range("n2").Select
    Do Until ActiveCell.EntireRow.Hidden = False
    ActiveCell.Offset(1, 0).Select
    Loop
    ActiveCell.Select
    ActiveCell.FormulaR1C1 = "QA"
    Application.CutCopyMode = False
    ActiveCell.Offset(0, -1).Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(0, 1).Select
    Range(Selection, Selection.End(xlUp)).Select
    Selection.FillDown
   
    
    'Else: Exit Sub
    'End If
    Selection.AutoFilter
    Range("D1").Select
    Selection.End(xlDown).Select
    ActiveCell.Offset(1, -3).Select
    Range(Selection, ActiveCell.SpecialCells(xlLastCell)).Select
    Range(Selection, Selection.End(xlToRight)).Select
    Selection.Delete Shift:=xlUp
    Sheets("Base").Select
    Application.DisplayAlerts = False
    ActiveWindow.SelectedSheets.Delete
    Application.DisplayAlerts = True
    Sheets("Daily Data").Select
    
   '---------------------------------------------------------------------
    'The below section added by Arunkumar_306102
    
    For i = 2 To 1048576
    
    If Sheets("Daily Data").Range("C" & i).Value = Empty Then
    Exit For
    End If
    Next i
    
    For ts = 3 To 1048576
    If Sheets("Base Data - DoNotChange").Range("B" & ts).Value = Empty Then
    Exit For
    End If
    Next ts
    
    For ddrow = 2 To i
    
    ' Market
    
    If Sheets("Daily Data").Range("O" & ddrow).Value = "Global" Or _
    Sheets("Daily Data").Range("O" & ddrow).Value = "Technical" Or _
    Sheets("Daily Data").Range("O" & ddrow).Value = "US-Grocery,Mexico,UK" Or _
    Sheets("Daily Data").Range("O" & ddrow).Value = "US-Sams,US-Grocery,Mexico,UK" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "All"
    
    ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "Argentina" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Brazil" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Canada" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Central America" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Chile" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "China" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Mexico,Argentina,Central America" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Mexico,Central America" Then
            
            Sheets("Daily Data").Range("AC" & ddrow).Value = "Intl"
            
     ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "Mexico" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Mexico,US-Sams" Or _
            Sheets("Daily Data").Range("O" & ddrow).Value = "Puerto Rico" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "MX"
            
    ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "US-SAMS" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "SAMS"

   ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "UK" Or _
        Sheets("Daily Data").Range("O" & ddrow).Value = "UK,MX" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "UK"
   
   ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "US-Grocery" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "USG"
    ElseIf Sheets("Daily Data").Range("O" & ddrow).Value = "US-Imports" Then

            Sheets("Daily Data").Range("AC" & ddrow).Value = "IDC"
            
    ElseIf Sheets("Daily Data").Range("Q" & ddrow).Value = "2012.4" Then
        
             Sheets("Daily Data").Range("AD" & ddrow).Value = "2012.4"
            
    End If
    
 'Ageing
    
    If Sheets("Daily Data").Range("Y" & ddrow).Value <= 5 Then
        Sheets("Daily Data").Range("Z" & ddrow).Value = "0_5"
    ElseIf (Sheets("Daily Data").Range("Y" & ddrow).Value >= 6 And Sheets("Daily Data").Range("Y" & ddrow).Value <= 10) Then
        Sheets("Daily Data").Range("Z" & ddrow).Value = "6_10"
    ElseIf (Sheets("Daily Data").Range("Y" & ddrow).Value >= 11 And Sheets("Daily Data").Range("Y" & ddrow).Value <= 15) Then
        Sheets("Daily Data").Range("Z" & ddrow).Value = "11_15"
    ElseIf Sheets("Daily Data").Range("Y" & ddrow).Value > 15 Then
        Sheets("Daily Data").Range("Z" & ddrow).Value = ">15"
    End If
    
'Type
If Day(DateTime.Date) <> "Monday" Then
        If Sheets("Daily Data").Range("D" & ddrow).Value = DateTime.Date - 1 Then
            Sheets("Daily Data").Range("AB" & ddrow).Value = "Inflow"
        Else
            Sheets("Daily Data").Range("AB" & ddrow).Value = "Backlog"
        End If
ElseIf Day(DateTime.Date) = "Monday" Then
        If Sheets("Daily Data").Range("D" & ddrow).Value = DateTime.Date - 1 Or _
            Sheets("Daily Data").Range("D" & ddrow).Value = DateTime.Date - 2 Or _
            Sheets("Daily Data").Range("D" & ddrow).Value = DateTime.Date - 3 Then
            Sheets("Daily Data").Range("AB" & ddrow).Value = "Inflow"
        Else
            Sheets("Daily Data").Range("AB" & ddrow).Value = "Backlog"
        End If
End If

    'Stage
    For BDDNC = 3 To ts
    If Sheets("Daily Data").Range("F" & ddrow).Value = Sheets("Base Data - DoNotChange").Range("B" & BDDNC).Value Then
     Sheets("Daily Data").Range("AA" & ddrow).Value = Sheets("Base Data - DoNotChange").Range("C" & BDDNC).Value
     Exit For
    End If
    Next BDDNC
    
    Next ddrow


'--------------------------------------------------------------------------------
    
    ActiveWorkbook.Save
    'MsgBox ("Success")
    
End Sub