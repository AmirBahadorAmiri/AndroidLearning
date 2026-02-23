package com.amirbahadoramiri.androidlearning.views.pdfmaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersianPdfActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();

        createReceiptPdfWithBitmap(this);

    }

    public Bitmap createBitmapFromView(View view) {

        int width = View.MeasureSpec.makeMeasureSpec(1080, View.MeasureSpec.EXACTLY);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

        view.measure(width, height);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        Bitmap bitmap = Bitmap.createBitmap(
                view.getMeasuredWidth(),
                view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888
        );

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }

    public void printPDF(Context context) {
        PrintManager printManager =
                (PrintManager) context.getSystemService(Context.PRINT_SERVICE);

        File file = new File(
                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "receipt.pdf"
        );

        PrintDocumentAdapter printAdapter = new PdfDocumentAdapter(file.getAbsolutePath());

        printManager.print("رسید رزرو", printAdapter,
                new PrintAttributes.Builder().build());

    }

    public void createReceiptPdf(Context context) {

        PdfDocument pdfDocument = new PdfDocument();

        // سایز A4 با رزولوشن 72dpi
        int pageWidth = 595;
        int pageHeight = 842;

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        Paint titlePaint = new Paint();
        titlePaint.setTextSize(18);
        titlePaint.setFakeBoldText(true);

        Typeface typeface = ResourcesCompat.getFont(context, R.font.vazir);

        Paint normalPaint = new Paint();
        normalPaint.setTextSize(14);
        normalPaint.setTypeface(typeface);


        int x = 40;
        int y = 60;

        // عنوان
        canvas.drawText("رسید رزرو اقامتگاه", x, y, titlePaint);

        y += 40;
        canvas.drawLine(x, y, pageWidth - 40, y, normalPaint);

        y += 40;

        // اطلاعات رزرو
        canvas.drawText("کد رزرو: RV2631765828294", x, y, normalPaint);
        y += 25;
        canvas.drawText("نام اقامتگاه: کاوان ویلا", x, y, normalPaint);
        y += 25;
        canvas.drawText("تاریخ ورود: 1404/11/21", x, y, normalPaint);
        y += 25;
        canvas.drawText("تاریخ خروج: 1404/11/24", x, y, normalPaint);
        y += 25;
        canvas.drawText("تعداد نفرات: 9 نفر", x, y, normalPaint);

        y += 50;
        canvas.drawLine(x, y, pageWidth - 40, y, normalPaint);

        y += 40;

        canvas.drawText("نام مسافر: خانم منا ابراهیمی", x, y, normalPaint);
        y += 25;
        canvas.drawText("شماره تماس: 09125993239", x, y, normalPaint);

        pdfDocument.finishPage(page);

        File file = new File(
                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "receipt.pdf"
        );

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            pdfDocument.close();
            printPDF(this);
            Toast.makeText(context, "PDF ساخته شد", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Logger.loge(e.getMessage());
        }

        pdfDocument.close();
    }

    public void createReceiptPdfWithBitmap(Context context) {

        PdfDocument pdfDocument = new PdfDocument();

        // سایز A4 با رزولوشن 72dpi
        int pageWidth = 595;
        int pageHeight = 842;

        PdfDocument.PageInfo pageInfo =
                new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();

        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        View v = getLayoutInflater().inflate(R.layout.activity_persian_pdf, null, false);
        Bitmap originalBitmap = createBitmapFromView(v);

        float scale = (float) pageWidth / originalBitmap.getWidth();
        int scaledHeight = (int) (originalBitmap.getHeight() * scale);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(
                originalBitmap,
                pageWidth,
                scaledHeight,
                true
        );

        canvas.drawBitmap(scaledBitmap, 0, 0, null);
        pdfDocument.finishPage(page);

        File file = new File(
                getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "receipt.pdf"
        );

        try {
            pdfDocument.writeTo(new FileOutputStream(file));
            pdfDocument.close();
            printPDF(this);
            Toast.makeText(context, "PDF ساخته شد", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Logger.loge(e.getMessage());
        }

        pdfDocument.close();
    }


}
