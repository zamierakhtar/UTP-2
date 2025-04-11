package lelang;

public class BarangLelang {
    protected String namaBarang;
    protected double hargaAwal;
    protected double penawaranTertinggi = 0;
    protected Peserta pemenang = null;
    protected boolean statusLelang = false;

    public BarangLelang(String namaBarang, double hargaAwal) {
        this.namaBarang = namaBarang;
        this.hargaAwal = hargaAwal;
    }

    public void mulaiLelang() {
        statusLelang = true;
        System.out.println("Lelang untuk " + namaBarang + " telah dimulai dengan harga awal Rp" + (int)hargaAwal);
    }

    public void terimaPenawaran(Peserta peserta, double jumlah) {
        if (!statusLelang) {
            System.out.println(peserta.getNama() + " menawar Rp" + (int)jumlah);
            System.out.println("Penawaran tidak valid! Lelang sudah ditutup\n");
            return;
        }

        System.out.println(peserta.getNama() + " menawar Rp" + (int)jumlah);

        if (jumlah <= penawaranTertinggi || jumlah <= hargaAwal) {
            System.out.println("Penawaran tidak valid! penawaran harus lebih tinggi dari Rp" + (int)Math.max(penawaranTertinggi, hargaAwal) + "\n");
        } else if (peserta.getSaldo() < jumlah) {
            System.out.println("Penawaran tidak valid! Saldo " + peserta.getNama() + " tidak mencukupi\n");
        } else {
            penawaranTertinggi = jumlah;
            pemenang = peserta;
            System.out.println("Penawaran disetujui!\n");
        }
    }

    public void tutupLelang() {
        statusLelang = false;
        if (pemenang != null) {
            pemenang.setSaldo(pemenang.getSaldo() - penawaranTertinggi);
            System.out.println("Lelang ditutup! " + namaBarang + " terjual dengan harga " + (int)penawaranTertinggi + " kepada " + pemenang.getNama());
            System.out.println("Sisa saldo " + pemenang.getNama() + ": Rp" + (int)pemenang.getSaldo());
        } else {
            System.out.println("Lelang ditutup! Tidak ada pemenang.");
        }
        System.out.println("=====================================\n");
    }
}
